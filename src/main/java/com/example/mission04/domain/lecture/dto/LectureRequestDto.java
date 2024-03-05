package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class LectureRequestDto {


    @Getter
    @AllArgsConstructor
    public static class CreateLectureRequestDto {
        @NotBlank(message = "제목을 입력해주세요.")
        private String name;
        @PositiveOrZero(message = "양의 정수가 필요합니다")
        private Integer price;
        @NotBlank(message = "소개를 입력해주세요.")
        private String lectureIntroduce;
        private CategoryType category;
        private Teacher teacher;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;

        public Lecture toEntity() {
            return Lecture.builder()
                    .name(this.name)
                    .price(this.price)
                    .lectureIntroduce(this.lectureIntroduce)
                    .category(this.category)
                    .teacher(this.teacher)
                    .createdAt(this.createdAt)
                    .build();
        }


    }


}
