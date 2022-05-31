package com.recoder.backend.dto.user.response;

import com.recoder.backend.domain.user.User;

import lombok.Data;

@Data
public class UserResponseDto {


	private Long userId;
	private String email;
	private String nickname;
	private String profilePhoto;
	private String domain;
	private String introduce;

	public UserResponseDto(User user) {
		this.userId = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.profilePhoto = user.getProfilePhoto();
		this.domain = user.getDomain();
		this.introduce = user.getIntroduce();
	}
}
