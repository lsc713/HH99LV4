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

    @Getter
    public static class EditCommentResponseDto {

        private final String contents;

        public EditCommentResponseDto(Comment comment) {
            this.contents = comment.getContents();
        }
    }

    @Getter
    public static class GetCommentResponseDto {

        private final String contents;
        private final String writer;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        public GetCommentResponseDto(Comment comment) {
            this.contents = comment.getContents();
            this.writer = comment.getMember().getEmail();
            this.createdAt = comment.getCreatedAt();
            this.modifiedAt = comment.getModifiedAt();
        }
    }
}
