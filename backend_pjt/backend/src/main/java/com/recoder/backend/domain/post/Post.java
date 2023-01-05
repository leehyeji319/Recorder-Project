package com.recoder.backend.domain.post;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.recoder.backend.domain.BaseEntity;
import com.recoder.backend.domain.category.Category;
import com.recoder.backend.domain.comment.Comment;
import com.recoder.backend.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post extends BaseEntity {
	private static final int MAX_NAME_LENGTH = 30;
	private static final int MAX_TAG_SIZE = 5;

	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user; //작성자

	private String title; //제목

	@Lob
	private String content; //내용

	private int hits = 0; //조회수

	private String summary;

	@Enumerated(EnumType.STRING)
	private Exposure exposure; // ALL, NEIGHBOR, NO

	private String thumbnail_image;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "category_id")
	private Category category;


	//1대 다 관계
	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<PostLike> postLikes = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<PostTag> postTags = new ArrayList<>();


	@Builder
	public Post(User user, String title, String content, int hits, String summary,
		Exposure exposure, String thumbnail_image, Category category) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.summary = summary;
		this.exposure = exposure;
		this.thumbnail_image = thumbnail_image;
		this.category = category;
	}

	//==비즈니스 로직==//

	//조회수
	public void addHits() {
		this.hits += 1;
	}

}
