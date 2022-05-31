package com.recoder.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recoder.backend.domain.user.User;
import com.recoder.backend.dto.user.request.UserSaveRequestDto;
import com.recoder.backend.dto.user.request.UserUpdateRequestDto;
import com.recoder.backend.dto.user.response.UserResponseDto;
import com.recoder.backend.dto.user.response.UserUpdateResponseDto;
import com.recoder.backend.exception.IllegalUserException;
import com.recoder.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	//생성
	@Transactional
	public Long saveUser(UserSaveRequestDto requestDto) {
		validateDuplicateEmail(requestDto.getEmail());
		validateDuplicateDomain(requestDto.getDomain());
		return userRepository.save(requestDto.toEntity()).getId();
	}

	//마이페이지 수정
	@Transactional
	public UserUpdateResponseDto updateUser(Long userId, UserUpdateRequestDto updateDto) {

		User findUser = userRepository.findById(userId).get();

		if (!findUser.getDomain().equals(updateDto.getDomain())) {
			validateDuplicateDomain(updateDto.getDomain());
			findUser.setDomain(updateDto.getDomain());
		}
		if (!findUser.getNickname().equals(updateDto.getNickname())) {
			findUser.setNickname(updateDto.getNickname());
		}
		findUser.setIntroduce(updateDto.getIntroduce());

		return new UserUpdateResponseDto(findUser.getEmail(), findUser.getNickname(),
			findUser.getDomain(), findUser.getIntroduce());
	}

	//조회
	public List<UserResponseDto> findAllUser() {
		List<User> allUser = userRepository.findAll();
		List<UserResponseDto> collect = allUser.stream()
			.map(UserResponseDto::new)
			.collect(Collectors.toList());

		return collect;
	}

	//삭제
	@Transactional
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}


	//==검증 로직==//
	public void validateDuplicateDomain(String domain) {
		if (userRepository.findByDomain(domain).isPresent()) {
			throw new IllegalUserException("이미 존재하는 도메인입니다.");
		}
	}

	public void validateDuplicateEmail(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new IllegalUserException("이미 존재하는 이메일입니다.");
		}
	}
}
