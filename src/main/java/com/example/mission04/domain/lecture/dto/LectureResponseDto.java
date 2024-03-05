package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.dto.TeacherResponseDto;
import com.example.mission04.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class LectureResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CraeteLectureResponseDto{
        private String name;
        private int price;
        private String lectureIntroduce;
        private CategoryType category;
        private Teacher teacher;

        public CraeteLectureResponseDto(Lecture lecture) {
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
        @NotBlank(message = "제목을 입력해주세요.")
        private String name;
        @NotBlank(message = "가격을 입력해주세요.")
        private Integer price;
        @NotBlank(message = "소개를 입력해주세요.")
        private String lectureIntroduce;
        @NotBlank(message = "SPRING || REACT || NODE")
        private CategoryType category;
        @NotBlank(message = "강사의 성함을 입력해주세요.")
        private Teacher teacher;

        public ReadLectureResponseDto(Lecture lecture) {
            this.name = lecture.getName();
            this.price = lecture.getPrice();
            this.lectureIntroduce = lecture.getLectureIntroduce();
            this.category = lecture.getCategory();
            this.teacher = lecture.getTeacher();
        }
    }
}
