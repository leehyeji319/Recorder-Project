package com.recoder.backend.apiController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recoder.backend.auth.dto.loginlogout.response.ApiResponse;
import com.recoder.backend.domain.comment.Comment;
import com.recoder.backend.dto.comment.CommentDto;
import com.recoder.backend.dto.comment.request.CommentSaveRequestDto;
import com.recoder.backend.dto.comment.request.CommentUpdateRequestDto;
import com.recoder.backend.dto.comment.response.CommentUpdateResponseDto;
import com.recoder.backend.repository.query.CommentQueryRepository;
import com.recoder.backend.service.CommentService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

	private final CommentService commentService;
	private final CommentQueryRepository commentQueryRepository;

	//생성
	@PostMapping("/board/comments/")
	public ResponseEntity<?> saveComment(@RequestBody CommentSaveRequestDto requestDto) {
		commentService.saveComment(requestDto);

		return ResponseEntity.ok(new ApiResponse(true, "Comment Created Successfully!"));
	}

	//조회
	@GetMapping("/board/comments")
	public Result findAllComments(
		@RequestParam(value = "offset", defaultValue = "0") int offset,
		@RequestParam(value = "limit", defaultValue = "100") int limit) {
		//XtoOne은 fetch join으로 가져옴
		List<Comment> comments = commentQueryRepository.findAllWithUserPost(offset, limit);

		List<CommentDto> result = comments.stream()
			.map(o -> new CommentDto(o))
			.collect(Collectors.toList());

		return new Result(result);
	}

	//수정
	@PutMapping("/board/comments/{commnet_id}")
	public CommentUpdateResponseDto updateComment(@PathVariable("comment_id") Long commentId, @RequestBody
		CommentUpdateRequestDto updateDto) {
		return commentService.updateComment(commentId, updateDto);
	}

	//삭제
	@DeleteMapping("/board/comments/{commnet_id}")
	public void deleteComment(@PathVariable("comment_id") Long commentId) {
		commentService.deleteComment(commentId);
	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
}
