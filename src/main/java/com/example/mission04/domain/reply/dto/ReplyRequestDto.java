package com.example.mission04.domain.reply.dto;

import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.reply.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ReplyRequestDto {

    @Getter
    public static class CreateReplyRequestDto {

        @NotBlank(message = "내용을 입력해주세요.")
        private String contents;

        public Reply toEntity(Member member, Comment comment) {
            return Reply.builder()
                    .contents(this.contents)
                    .member(member)
                    .comment(comment)
                    .build();
        }
    }

    @Getter
    public static class EditReplyRequestDto {

        @NotBlank(message = "내용을 입력해주세요.")
        private String contents;
    }
}
