package com.recoder.backend.dto.post.response;

import java.time.LocalDateTime;

import com.recoder.backend.domain.post.Post;

public class PostAllUserResponseDto {
	private Long userId;
	private Long postId;

	private String title;
	private String content;
	private String summary; // 이걸 굳이 써머리로 넘겨줄 필요가 있을까 ?
	private LocalDateTime updatedAt;

	public PostAllUserResponseDto(Post post) {
		postId = post.getId();
		userId = post.getUser().getId();
		title = post.getTitle();
		content = post.getContent();
		summary = post.getSummary();
		updatedAt = post.getUpdatedAt();
	}
}
