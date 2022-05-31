package com.recoder.backend.repository.query;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.recoder.backend.domain.post.Post;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

	private final EntityManager em;

	@Transactional
	public List<Post> findAllWithUserCategory(int offset, int limit) {
		return em.createQuery(
				"select p from Post p" +
					" join fetch p.user u" +
					" join fetch p.category c", Post.class)
			.setFirstResult(offset)
			.setMaxResults(limit)
			.getResultList();
	}
}
