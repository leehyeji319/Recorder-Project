package com.recoder.backend.dto.user.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateResponseDto {

	private String email;
	private String nickname;
	private String domain;
	private String introduce;

}
