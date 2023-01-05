package com.recoder.backend.domain.alert;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.recoder.backend.domain.BaseEntity;
import com.recoder.backend.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Alert extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "alert_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String content;
}
