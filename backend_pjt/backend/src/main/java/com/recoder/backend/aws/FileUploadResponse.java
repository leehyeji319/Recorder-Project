package com.recoder.backend.aws;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileUploadResponse {

	Long userId;
	String fileName;
	String url;

	public FileUploadResponse(Long userId, String fileName, String url) {
		this.userId = userId;
		this.fileName = fileName;
		this.url = url;
	}
}
