package com.recoder.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recoder.backend.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllByUserId(Long userId);
}
