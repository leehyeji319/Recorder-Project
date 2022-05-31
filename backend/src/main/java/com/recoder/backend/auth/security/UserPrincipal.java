package com.recoder.backend.auth.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recoder.backend.domain.user.User;

import lombok.Getter;

@Getter
public class UserPrincipal implements UserDetails {

	private Long id;

	@JsonIgnore
	private String email;

	//private String username;

	@JsonIgnore
	private String password;

	private String nickname;

	private String domain;

	private String introduce;

	private String profilePhoto;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String email, String password, String nickname, String domain,
		String introduce, String profilePhoto,
		Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		//this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.domain = domain;
		this.introduce = introduce;
		this.profilePhoto = profilePhoto;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
			new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());

		return new UserPrincipal(
			user.getId(),
			user.getEmail(),
			//user.getUsername(),
			user.getPassword(),
			user.getNickname(),
			user.getDomain(),
			user.getIntroduce(),
			user.getProfilePhoto(),
			authorities
		);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		//return username;
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
