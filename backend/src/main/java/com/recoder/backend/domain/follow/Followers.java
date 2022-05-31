package com.recoder.backend.domain.follow;

import static javax.persistence.FetchType.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.recoder.backend.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Followers {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
