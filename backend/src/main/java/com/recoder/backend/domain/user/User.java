package com.recoder.backend.domain.user;

import static javax.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.recoder.backend.domain.BaseEntity;
import com.recoder.backend.domain.category.Category;
import com.recoder.backend.domain.comment.Comment;
import com.recoder.backend.domain.follow.Followers;
import com.recoder.backend.domain.follow.Followings;
import com.recoder.backend.domain.post.PostLike;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(unique = true)
	private String email;

	//private String username;

	private String password;

	private String nickname;

	private String profilePhoto;

	private String domain;

	private String introduce;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "refreshtoken", referencedColumnName = "id")
	// private RefreshToken refreshToken;

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Category> categoryList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Comment> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<PostLike> postLikeList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Followings> followingsList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Followers> followersList = new ArrayList<>();

	public User() {

	}

	@Builder
	public User(String email, String password, String nickname, String profilePhoto, String domain,
		String introduce) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.profilePhoto = profilePhoto;
		this.domain = domain;
		this.introduce = introduce;
	}

	@Builder
	public User(String email, String password, String nickname, String domain,
		String introduce) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.domain = domain;
		this.introduce = introduce;
	}

}
