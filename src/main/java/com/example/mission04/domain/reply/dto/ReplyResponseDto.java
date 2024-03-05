package com.example.mission04.domain.reply.dto;

import com.example.mission04.domain.reply.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReplyResponseDto {

    @Getter
    public static class CreateReplyResponseDto {

        private final String contents;
        private final LocalDateTime createdAt;

        public CreateReplyResponseDto(Reply reply) {
            this.contents = reply.getContents();
            this.createdAt = reply.getCreatedAt();
        }
    }

    @Getter
    public static class EditReplyResponseDto {

        private final String contents;

        public EditReplyResponseDto(Reply reply) {
            this.contents = reply.getContents();
        }
    }

    @Getter
    public static class GetReplyResponseDto {

        private final String contents;
        private final String writer;
        private final LocalDateTime createdAt;

        public GetReplyResponseDto(Reply reply) {
            this.contents = reply.getContents();
            this.writer = reply.getMember().getEmail();
            this.createdAt = reply.getCreatedAt();
        }
    }
}
