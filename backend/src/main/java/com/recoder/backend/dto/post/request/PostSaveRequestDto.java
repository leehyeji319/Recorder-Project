package com.recoder.backend.dto.post.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.recoder.backend.domain.category.Category;
import com.recoder.backend.domain.post.Post;
import com.recoder.backend.domain.user.User;

import lombok.Data;
@Data
public class PostSaveRequestDto {


	private Long postId;
	private Category category;
	private User user;

	private Long userId;
	private Long categoryId; //여기 구현 제대로 하기

	@NotEmpty
	@Size(min = 1, max = 20, message = "1자 이상 20자 이하로 작성하여야 합니다.")
	private String title;
	@NotEmpty
	@Size(min = 1)
	private String content;
	private String summary;
	private String exposure;
	private String thumbnailImage;

	public Post toEntity() {
		return Post.builder()
			.user(this.user)
			.category(this.category)
			.title(this.title)
			.content(this.content)
//			.summary(this.summary)
//			.exposure(Exposure.valueOf(this.exposure))
//			.thumbnail_image(this.thumbnailImage)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
