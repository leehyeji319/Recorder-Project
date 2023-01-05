package com.recoder.backend.dto.comment;

import com.recoder.backend.domain.comment.Comment;

import lombok.Data;

//조회 Dto
@Data
public class CommentDto {

	private Long commentId;
	private String content;

	public CommentDto(Comment comment) {
		commentId = comment.getId();
		content = comment.getContent();
	}
}
