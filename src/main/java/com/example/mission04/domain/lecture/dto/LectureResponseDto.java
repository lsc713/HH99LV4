package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LectureResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CreateLectureResponseDto{
        @NotBlank(message = "강의 이름을 입력하세요")
        private String name;
        @PositiveOrZero(message = "양의 정수를 입력하세요")
        private int price;
        @NotBlank(message = "강의 소개를 적어주세요")
        private String lectureIntroduce;
        @NotNull(message = "SPRING || REACT || NODE")
        private CategoryType category;
        @NotNull(message = "강사의 성함을 입력하세요")
        private Teacher teacher;

        public CreateLectureResponseDto(Lecture lecture) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.lectureIntroduce = lecture.getLectureIntroduce();
            this.category = lecture.getCategory();
            this.teacher = lecture.getTeacher();
        }

    }

    @Getter
    @AllArgsConstructor
    public static class ReadLectureResponseDto {
        private String name;
        private Integer price;
        private String lectureIntroduce;
        private CategoryType category;
        private Integer like;

        public ReadLectureResponseDto(Lecture lecture, int likeCount) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.lectureIntroduce = lecture.getLectureIntroduce();
            this.category = lecture.getCategory();
            this.like = likeCount;
        }
    }
}
