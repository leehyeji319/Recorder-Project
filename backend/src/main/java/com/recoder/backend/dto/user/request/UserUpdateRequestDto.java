package com.recoder.backend.dto.user.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto {

	@NotEmpty
	private String nickname;

	@NotEmpty
	private String domain;

	@Size(max = 30)
	private String introduce;
}
