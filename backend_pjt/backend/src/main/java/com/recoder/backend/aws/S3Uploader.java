package com.recoder.backend.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.recoder.backend.domain.user.User;
import com.recoder.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
@Transactional(readOnly = true)
public class S3Uploader {

	private final AmazonS3Client amazonS3Client;
	private final UserRepository userRepository;

	public static final String key = "/photo";

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	/*
	* 프로필 파일 업로드 부분
	* 저장 파일 디렉토리 : 사용자ID + /photo
	* */
	@Transactional
	public FileUploadResponse uploadProfile(Long userId, MultipartFile multipartFile, String dirName) throws
		IOException {

		File uploadFile = convert(multipartFile)
			.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

		return uploadProfile(userId, uploadFile, dirName);
	}

	@Transactional
	private FileUploadResponse uploadProfile(Long userId, File uploadFile, String dirName) {
		String fileName = dirName + "/" + userId + key;
		String uploadImageUrl = putS3(uploadFile, fileName);
		removeNewFile(uploadFile);

		User user = userRepository.findById(userId).get();
		user.setProfilePhoto(uploadImageUrl);

		return new FileUploadResponse(userId, fileName, uploadImageUrl);
	}

	private String putS3(File uploadFile, String fileName) {
		amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
			CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}

	/*
	 * 포스트 파일 업로드 부분
	 * 저장 파일 디렉토리 : 사용자ID + randomUUID;
	 * */
	@Transactional
	public FileUploadResponse uploadPostPhoto(Long userId, MultipartFile multipartFile, String dirName) throws
		IOException {
		File uploadFile = convert(multipartFile)
			.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

		return uploadPostPhoto(userId, uploadFile, dirName);
	}

	@Transactional
	private FileUploadResponse uploadPostPhoto(Long userId, File uploadFile, String dirName) {
		String fileName = dirName + "/" + UUID.randomUUID();
		String uploadImageUrl = putS3PostPhoto(uploadFile, fileName);
		removeNewFile(uploadFile);
		return new FileUploadResponse(userId, fileName, uploadImageUrl);
	}

	private String putS3PostPhoto(File uploadFile, String fileName) {
		amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
			CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}

	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("파일이 삭제되었습니다.");
		} else {
			log.info("파일이 삭제되지 못했습니다.");
		}
	}

	private Optional<File> convert(MultipartFile file) throws IOException {
		File convertFile = new File(file.getOriginalFilename());
		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}

		return Optional.empty();
	}

	// delete file
	@Transactional
	public void fileDelete(Long userId) {
		/*if (!amazonS3Client.doesObjectExist(bucket, "profile/" + userId + "/photo")) {
			throw new AmazonS3Exception("Object" + userId + "does not exist!");
		}
		amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, "profile/" + userId + "/photo"));*/

		User user = userRepository.findById(userId).get();
		user.setProfilePhoto(null);
	}

}