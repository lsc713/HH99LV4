package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
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
}
