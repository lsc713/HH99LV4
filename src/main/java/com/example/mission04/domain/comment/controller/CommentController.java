package com.example.mission04.domain.comment.controller;

import com.example.mission04.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.example.mission04.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.example.mission04.domain.comment.service.CommentService;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
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
    public ResponseDto<CreateCommentResponseDto> create(
            @PathVariable Long lectureId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateCommentRequestDto requestDto
    ) {
        CreateCommentResponseDto responseDto = commentService.create(lectureId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("선택한 강의 댓글 기능", responseDto);
    }
}
