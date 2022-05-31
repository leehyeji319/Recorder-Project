package com.recoder.backend.domain.tag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.recoder.backend.domain.BaseEntity;
import com.recoder.backend.domain.post.PostTag;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Tag extends BaseEntity {

	private static final int MAX_TAG_LENGTH = 20;

	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	private Long id;

	@Column
	private String tagName;

	@OneToMany(mappedBy = "tag")
	private List<PostTag> postTags = new ArrayList<>();

}
