package com.recoder.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recoder.backend.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
