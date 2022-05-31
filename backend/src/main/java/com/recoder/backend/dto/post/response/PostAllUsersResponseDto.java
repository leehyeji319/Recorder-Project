package com.recoder.backend.dto.post.response;

import com.recoder.backend.domain.post.Post;

import lombok.Data;

@Data
public class PostAllUsersResponseDto {
	private Long postId;
	private Long userId;
	private Long categoryId;
	private String userNickname;
	private String profilePhoto;
	private String domain;
	private String introduce;
	private String categoryName;
	private String title;
	private String content;
	private String summary;
	private int hits;
	private String thumbnailImage;

	public PostAllUsersResponseDto(Post post) {
		this.postId = post.getId();
		this.userId = post.getUser().getId();
		this.categoryId = post.getCategory().getId();
		this.userNickname = post.getUser().getNickname();
		this.profilePhoto = post.getUser().getProfilePhoto();
		this.domain = post.getUser().getDomain();
		this.introduce = post.getUser().getIntroduce();
		this.categoryName = post.getCategory().getName();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.summary = post.getSummary();
		this.hits = post.getHits();
		this.thumbnailImage = post.getThumbnail_image();
	}

}
