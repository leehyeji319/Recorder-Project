package com.recoder.backend.dto.post;

import com.recoder.backend.domain.post.Exposure;
import com.recoder.backend.domain.post.Post;

import lombok.Data;
//조회 Dto

@Data
public class PostDto {

	private Long postId;
	private Long userId;
	private Long categoryId;
	private String userNickname;
	private String CategoryName;
	private String title;
	private String content;
	private String summary;
	private int hits;
	private Exposure exposure;
	private String thumbnailImage;

	public PostDto(Post post) {
		this.postId = post.getId();
		this.userId = post.getUser().getId();
		this.categoryId = post.getCategory().getId();
		this.userNickname = post.getUser().getNickname();
		this.CategoryName = post.getCategory().getName();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.summary = post.getSummary();
		this.hits = post.getHits();
		this.exposure = post.getExposure();
		this.thumbnailImage = post.getThumbnail_image();
	}
}
