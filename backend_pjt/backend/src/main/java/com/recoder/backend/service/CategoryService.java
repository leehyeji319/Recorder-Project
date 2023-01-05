package com.recoder.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recoder.backend.domain.category.Category;
import com.recoder.backend.domain.user.User;
import com.recoder.backend.dto.category.request.CategorySaveRequestDto;
import com.recoder.backend.dto.category.request.CategoryUpdateRequestDto;
import com.recoder.backend.dto.category.response.CategoryNameDto;
import com.recoder.backend.dto.category.response.CategoryUpdateResponseDto;
import com.recoder.backend.exception.IllegalUserException;
import com.recoder.backend.repository.CategoryRepository;
import com.recoder.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	//카테고리 생성
	@Transactional
	public Category saveCategory(CategorySaveRequestDto requestDto) {
		User user = userRepository.findById(requestDto.getUserId()).get();
		requestDto.setUser(user);

		validateDuplicatedCategoryName(requestDto.getUserId(), requestDto.getCategoryName());
		return categoryRepository.save(requestDto.toEntity());
	}

	//카테고리 수정
	@Transactional
	public CategoryUpdateResponseDto updateCategory(Long categoryId, CategoryUpdateRequestDto updateDto) {
		Category findCategory = categoryRepository.findById(categoryId).get();
		Long userId = categoryRepository.findUserIdByCategoryId(categoryId);
		if (!findCategory.getName().equals(updateDto.getCategoryName())) {
			validateDuplicatedCategoryName(userId, updateDto.getCategoryName());
			findCategory.setName(updateDto.getCategoryName());
		}
		return new CategoryUpdateResponseDto(findCategory.getId(), findCategory.getName());
	}

	//카테고리 삭제
	@Transactional
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	//글 작성 폼에 카테고리 뿌려주기, 카테고리 조회시 씀
	public List<CategoryNameDto> showCategoryNames(Long userId) {
//		return categoryRepository.findAll()
		return categoryRepository.findAllByUserId(userId)
			.stream()
			.map(CategoryNameDto::new)
			.collect(Collectors.toList());
	}

	private void validateDuplicatedCategoryName(Long userId, String categoryName) {
		if (categoryRepository.existCategoryByUserId(userId, categoryName) != null) {
			throw new IllegalUserException("이미 존재하는 카테고리입니다.");
		}
	}
}
