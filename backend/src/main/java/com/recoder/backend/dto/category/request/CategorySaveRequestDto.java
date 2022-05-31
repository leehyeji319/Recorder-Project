package com.recoder.backend.dto.category.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.recoder.backend.domain.category.Category;
import com.recoder.backend.domain.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategorySaveRequestDto {

	private User user;
	private Long userId;

	@NotEmpty
	@Length(max = 10)
	private String categoryName;

	public Category toEntity() {
		return Category.builder()
			.user(this.user)
			.name(this.categoryName)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}

}
