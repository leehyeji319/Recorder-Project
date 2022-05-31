package com.recoder.backend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {

	private Long id;
	private String email;
	private String nickname;
	private String domain;

	public UserSummary(Long id, String email, String nickname, String domain) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.domain = domain;
	}
}
