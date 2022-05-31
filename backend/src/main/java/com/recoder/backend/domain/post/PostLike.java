package com.recoder.backend.domain.post;

import static javax.persistence.FetchType.*;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.recoder.backend.domain.user.User;
import com.recoder.backend.exception.IllegalUserException;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class PostLike {

	@Id @GeneratedValue
	@Column(name = "post_like_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public PostLike(Long id, Post post, User user) {
		validateNotNull(post, user);
		this.id = id;
		this.post = post;
		this.user = user;
	}

	public PostLike(Post post, User user) {
		this(null, post, user);
	}

	private void validateNotNull(Post post, User user) {
		if (Objects.isNull(post)) {
			throw new IllegalUserException("포스트 필요");
		}
		if (Objects.isNull(user)) {
			throw new IllegalUserException("유저 아이디 필요");
		}
	}

}
