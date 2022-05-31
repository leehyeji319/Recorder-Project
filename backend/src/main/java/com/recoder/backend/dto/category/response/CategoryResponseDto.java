package com.recoder.backend.dto.category.response;

import java.util.List;
import java.util.stream.Collectors;

import com.recoder.backend.domain.category.Category;
import com.recoder.backend.dto.category.CategoryPostsDto;

import lombok.Data;

@Data
public class CategoryResponseDto {

	private Long categoryId;
	private Long userId;
	private String categoryName;

	private List<CategoryPostsDto> posts;

	public CategoryResponseDto(Category category) {
		categoryId = category.getId();
		//userId = category.getUser().getId();
		categoryName = category.getName();
		posts = category.getPosts().stream()
			.map(post -> new CategoryPostsDto(post))
			.collect(Collectors.toList());
	}
}
