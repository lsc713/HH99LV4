package com.example.mission04.domain.comment.dto;

import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class CommentRequestDto {

    @Getter
    public static class CreateCommentRequestDto {

        @NotBlank(message = "내용을 입력해주세요.")
        private String contents;

        public Comment toEntity(Member member, Lecture lecture) {
            return Comment.builder()
                    .contents(this.contents)
                    .member(member)
                    .lecture(lecture)
                    .build();
        }
    }

    @Getter
    public static class EditCommentRequestDto {

        @NotBlank(message = "내용을 입력해주세요.")
        private String contents;
    }
}
