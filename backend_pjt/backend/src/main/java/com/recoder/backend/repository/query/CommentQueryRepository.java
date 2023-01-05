package com.recoder.backend.repository.query;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.recoder.backend.domain.comment.Comment;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

	private final EntityManager em;

	public List<Comment> findAllWithUserPost(int offset, int limit) {
		return em.createQuery(
				"select c from Comment c" +
					" join fetch c.user u" +
					" join fetch c.post p", Comment.class)
			.setFirstResult(offset)
			.setMaxResults(limit)
			.getResultList();
	}
}
