package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.comment.dto.CommentResponseDto.GetCommentResponseDto;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class LectureResponseDto {

    @Getter
    public static class CreateLectureResponseDto {

        private final String name;
        private final Integer price;
        private final String introduction;
        private final String category;
        private final Long teacherId;
        private final LocalDateTime createdAt;

        public CreateLectureResponseDto(Lecture lecture) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.teacherId = lecture.getTeacher().getId();
            this.createdAt = lecture.getCreatedAt();
        }
    }

    @Getter
    public static class GetLectureResponseDto {

        private final String name;
        private final Integer price;
        private final String introduction;
        private final String category;
        private final LocalDateTime createdAt;
        private final Long likes;
        private final GetTeacherResponseDto teacher;

        public GetLectureResponseDto(Lecture lecture, Long likes) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.createdAt = lecture.getCreatedAt();
            this.likes = likes;
            this.teacher = new GetTeacherResponseDto(lecture.getTeacher());
        }
    }

    @Getter
    public static class SearchLectureResponseDto {

        private final String name;
        private final Integer price;
        private final String introduction;
        private final String category;
        private final LocalDateTime createdAt;
        private final List<GetCommentResponseDto> comments;

        public SearchLectureResponseDto(Lecture lecture) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.createdAt = lecture.getCreatedAt();
            this.comments = lecture.getCommentList().stream().map(GetCommentResponseDto::new).toList();
        }
    }
}
