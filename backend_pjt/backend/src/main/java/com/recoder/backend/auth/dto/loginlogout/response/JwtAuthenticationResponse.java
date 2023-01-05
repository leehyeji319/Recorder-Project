package com.recoder.backend.auth.dto.loginlogout.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtAuthenticationResponse {

	private String accessToken;
	//private String refreshToken;
	private String tokenType = "Bearer";
	private Long userId;
	private String email;
	private String domain;
	private String introduce;

	public JwtAuthenticationResponse(String accessToken,
		Long userId, String email, String domain, String introduce) {
		this.accessToken = accessToken;
		//this.refreshToken = refreshToken;
		this.tokenType = tokenType;
		this.userId = userId;
		this.email = email;
		this.domain = domain;
		this.introduce = introduce;
	}

}
