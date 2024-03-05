package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LectureResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CreateLectureResponseDto{
        private String name;
        private int price;
        private String lectureIntroduce;
        private CategoryType category;
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
