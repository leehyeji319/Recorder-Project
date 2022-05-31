package com.recoder.backend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserIdentityAvailability {

	private Boolean available;

	public UserIdentityAvailability(Boolean available) {
		this.available = available;
	}
}
