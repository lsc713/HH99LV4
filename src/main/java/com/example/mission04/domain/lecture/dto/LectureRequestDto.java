package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

public class LectureRequestDto {

    @Getter
    public static class CreateLectureRequestDto {

        @NotBlank(message = "강의명을 입력해주세요.")
        private String name;

        @PositiveOrZero(message = "가격을 입력해주세요.")
        private Integer price;

        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;

        private CategoryType category;

        @PositiveOrZero(message = "강사 번호를 입력해주세요.")
        private Long teacherId;

        public Lecture toEntity(Teacher teacher) {
            return Lecture.builder()
                    .name(this.name)
                    .price(this.price)
                    .introduction(this.introduction)
                    .category(this.category)
                    .teacher(teacher)
                    .build();
        }
    }
}
