package com.recoder.backend.dto.user.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.recoder.backend.domain.user.User;

import lombok.Data;

@Data
public class UserSaveRequestDto {

	private Long userId;
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;
	@NotEmpty
	private String nickname;
	private String picture;
	@NotEmpty
	private String domain;
	@Size(max = 20)
	private String introduce;

	public User toEntity() {
		return User.builder()
			.email(this.email)
			.password(this.password)
			.nickname(this.nickname)
			.profilePhoto(this.picture)
			.domain(this.domain)
			.introduce(this.introduce)
			.build();
	}


}
