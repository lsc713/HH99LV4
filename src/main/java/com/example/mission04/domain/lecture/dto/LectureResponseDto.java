package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;

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
        private final GetTeacherResponseDto teacher;

        public GetLectureResponseDto(Lecture lecture) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.createdAt = lecture.getCreatedAt();
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

        public SearchLectureResponseDto(Lecture lecture) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.createdAt = lecture.getCreatedAt();
        }
    }
}
