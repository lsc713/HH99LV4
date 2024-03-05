package com.example.mission04.domain.reply.controller;

import com.example.mission04.domain.reply.dto.ReplyRequestDto.CreateReplyRequestDto;
import com.example.mission04.domain.reply.dto.ReplyRequestDto.EditReplyRequestDto;
import com.example.mission04.domain.reply.dto.ReplyResponseDto.CreateReplyResponseDto;
import com.example.mission04.domain.reply.dto.ReplyResponseDto.EditReplyResponseDto;
import com.example.mission04.domain.reply.dto.ReplyResponseDto.GetReplyResponseDto;
import com.example.mission04.domain.reply.service.ReplyService;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "replies", description = "대댓글 관련 API")
@RequestMapping("/api/v1/comments")
@RestController
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/{commentId}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "대댓글 등록 기능", description = "대댓글을 등록할 수 있는 API")
    public ResponseDto<CreateReplyResponseDto> create(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateReplyRequestDto requestDto
    ) {
        CreateReplyResponseDto responseDto = replyService.create(commentId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("대댓글 작성 기능", responseDto);
    }

    @PutMapping("/{commentId}/replies/{replyId}")
    @Operation(summary = "대댓글 수정 기능", description = "대댓글을 수정할 수 있는 API")
    public ResponseDto<EditReplyResponseDto> edit(
            @PathVariable Long commentId,
            @PathVariable Long replyId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditReplyRequestDto requestDto
    ) {
        EditReplyResponseDto responseDto = replyService.edit(commentId, replyId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("대댓글 수정 기능", responseDto);
    }

    @DeleteMapping("/{commentId}/replies/{replyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "대댓글 삭제 기능", description = "대댓글을 삭제할 수 있는 API")
    public ResponseDto<Void> delete(
            @PathVariable Long commentId,
            @PathVariable Long replyId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        replyService.delete(commentId, replyId, userDetails.getUsername());
        return ResponseDto.success("대댓글 삭제 기능", null);
    }

    @GetMapping("/{commentId}/replies")
    @Operation(summary = "댓글의 모든 대댓글 조회 기능", description = "댓글에 작성된 모든 대댓글을 조회할 수 있는 API")
    public ResponseDto<List<GetReplyResponseDto>> getRepliesOfComment(
            @PathVariable Long commentId
    ) {
        List<GetReplyResponseDto> responseDtoList = replyService.getRepliesOfComment(commentId);
        return ResponseDto.success("댓글의 모든 대댓글 조회 기능", responseDtoList);
    }
}
