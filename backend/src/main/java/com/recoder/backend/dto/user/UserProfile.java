package com.recoder.backend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserProfile {

	private String email;
	private String nickname;
	private String profilePhoto;
	private String domain;
	private String introduce;

	public UserProfile(String email, String nickname, String profilePhoto, String domain, String introduce) {
		this.email = email;
		this.nickname = nickname;
		this.profilePhoto = profilePhoto;
		this.domain = domain;
		this.introduce = introduce;
	}
}
