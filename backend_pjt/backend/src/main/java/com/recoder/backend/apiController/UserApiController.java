package com.recoder.backend.apiController;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recoder.backend.auth.security.CurrentUser;
import com.recoder.backend.auth.security.UserPrincipal;
import com.recoder.backend.aws.FileUploadResponse;
import com.recoder.backend.aws.S3Uploader;
import com.recoder.backend.domain.user.User;
import com.recoder.backend.dto.user.UserIdentityAvailability;
import com.recoder.backend.dto.user.UserProfile;
import com.recoder.backend.dto.user.UserSummary;
import com.recoder.backend.dto.user.request.UserSaveRequestDto;
import com.recoder.backend.dto.user.request.UserUpdateRequestDto;
import com.recoder.backend.dto.user.response.UserResponseDto;
import com.recoder.backend.dto.user.response.UserUpdateResponseDto;
import com.recoder.backend.exception.ResourceNotFoundException;
import com.recoder.backend.repository.UserRepository;
import com.recoder.backend.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserRepository userRepository;
	private final UserService userService;
	private final S3Uploader s3Uploader;

	//생성
	@PostMapping("/users/account/signup")
	public Long saveUser(@RequestBody UserSaveRequestDto requestDto) {
		return userService.saveUser(requestDto);
	}

	//수정
	@PutMapping("/users/{user_id}")
	public UserUpdateResponseDto updateUser(@PathVariable("user_id") Long userId,
		@RequestBody UserUpdateRequestDto updateDto) {
		return userService.updateUser(userId, updateDto);
	}

	//유저 프로필 업로드
	@PostMapping("/users/{user_id}/profilePhoto")
	public ResponseEntity<?> uploadProfilePhoto(@PathVariable("user_id") Long userId, @RequestParam("profilePhoto") MultipartFile multipartFile) throws IOException {
		//S3 Bucket 내부에 "/profile"

		FileUploadResponse profile = s3Uploader.uploadProfile(userId, multipartFile, "profile");
		return ResponseEntity.ok(profile);
	}

	//유저 프로필 삭제
	@DeleteMapping("/users/{user_id}/profilePhoto")
	public ResponseEntity<?> deleteProfilePhoto(@PathVariable("user_id") Long userId) {
		s3Uploader.fileDelete(userId);

		return ResponseEntity.ok("delete success!");
	}

	//조회
	@GetMapping("/users")
	public Result allUsers() {
		List<UserResponseDto> allUser = userService.findAllUser();

		return new Result(allUser);
	}

	// 헤더부분에 표현할 유저 써머리
	@GetMapping("/users/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {

		UserSummary userSummary = new UserSummary(
			currentUser.getId(), currentUser.getEmail(), currentUser.getNickname(), currentUser.getDomain()
		);

		return userSummary;
	}

	// 마이페이지에 보여줄 유저 dto
	@GetMapping("/users/{user_id}")
	public UserProfile getUserProfile(@PathVariable(value = "user_id") Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

		UserProfile userProfile = new UserProfile(
			user.getEmail(), user.getNickname(), user.getProfilePhoto(), user.getDomain(), user.getIntroduce()
		);

		return userProfile;
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkDomainAvailability(@RequestParam(value = "domain") String domain) {
		Boolean isAvailable = !userRepository.existsByDomain(domain);
		return new UserIdentityAvailability(isAvailable);
	}

	//삭제
	@DeleteMapping("/users/{user_id}")
	public void deleteUser(@PathVariable("user_id") Long userId) {
		userService.deleteUser(userId);
	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
}
