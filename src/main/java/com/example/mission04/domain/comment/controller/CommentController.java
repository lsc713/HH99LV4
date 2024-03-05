package com.example.mission04.domain.comment.controller;

import com.example.mission04.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.example.mission04.domain.comment.dto.CommentRequestDto.EditCommentRequestDto;
import com.example.mission04.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.example.mission04.domain.comment.dto.CommentResponseDto.EditCommentResponseDto;
import com.example.mission04.domain.comment.service.CommentService;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/lectures")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{lectureId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCommentResponseDto> create(
            @PathVariable Long lectureId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateCommentRequestDto requestDto
    ) {
        CreateCommentResponseDto responseDto = commentService.create(lectureId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("선택한 강의 댓글 추가", responseDto);
    }

    @PutMapping("/{lectureId}/comments/{commentId}")
    public ResponseDto<EditCommentResponseDto> edit(
            @PathVariable Long lectureId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditCommentRequestDto requestDto
    ) {
        EditCommentResponseDto responseDto = commentService.edit(lectureId, commentId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("선택한 강의 댓글 수정", responseDto);
    }

    @DeleteMapping("/{lectureId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<EditCommentResponseDto> delete(
            @PathVariable Long lectureId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.delete(lectureId, commentId, userDetails.getUsername());
        return ResponseDto.success("선택한 강의 댓글 삭제", null);
    }
}
