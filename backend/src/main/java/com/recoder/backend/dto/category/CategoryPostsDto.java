package com.recoder.backend.dto.category;

import com.recoder.backend.domain.post.Post;

import lombok.Data;

/**
 * 카테고리 조회시 딸려있는 포스트 가져오기
 */
@Data
public class CategoryPostsDto {

	private String title;
	private String content;
	private int hits;
	private String summary;
	private String exposure;
	private String thumbnailImage;


	public CategoryPostsDto(Post post) {
		title = post.getTitle();
		content = post.getContent();
		hits = post.getHits();
		summary = post.getSummary();
		exposure = String.valueOf(post.getExposure());
		thumbnailImage = post.getThumbnail_image();
	}
}
