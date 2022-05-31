package com.recoder.backend.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostUpdateResponseDto {

	private Long postId;
	private Long categoryId;
	private String title;
	private String content;
	private String exposure;
	private String thumbnailImage;
}
