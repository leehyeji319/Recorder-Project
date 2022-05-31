package com.recoder.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recoder.backend.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String categoryName);

	List<Category> findAllByUserId(Long userId);

	@Query("SELECT c.name FROM Category c WHERE c.user.id = :userId and c.name = :categoryName")
	String existCategoryByUserId(@Param("userId") Long userId, @Param("categoryName") String categoryName);

	@Query("SELECT c.user.id FROM Category c WHERE c.id = :categoryId")
	Long findUserIdByCategoryId(@Param("categoryId") Long categoryId);
}

