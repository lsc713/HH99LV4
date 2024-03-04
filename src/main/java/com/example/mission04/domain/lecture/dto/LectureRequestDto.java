package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LectureRequestDto {


    @Getter
    @AllArgsConstructor
    public class CreateLectureRequestDto {
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

        public Lecture toEntity() {
            return Lecture.builder()
                    .name(this.name)
                    .price(this.price)
                    .lectureIntroduce(this.lectureIntroduce)
                    .category(this.category)
                    .teacher(this.teacher)
                    .build();
        }


    }
}
