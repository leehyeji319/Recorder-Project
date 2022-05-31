package com.recoder.backend.dto.category.response;

import com.recoder.backend.domain.category.Category;

import lombok.Data;

@Data
public class CategoryNameDto {
	private Long categoryId;
	private String categoryName;

	public CategoryNameDto(Category entity) {
		this.categoryId = entity.getId();
		this.categoryName = entity.getName();
	}
}
