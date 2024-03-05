package com.example.mission04.domain.comment.dto;

import com.example.mission04.domain.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

public class CommentResponseDto {

    @Getter
    public static class CreateCommentResponseDto {

        private final String contents;
        private final LocalDateTime createdAt;

        public CreateCommentResponseDto(Comment comment) {
            this.contents = comment.getContents();
            this.createdAt = comment.getCreatedAt();
        }
    }
}
