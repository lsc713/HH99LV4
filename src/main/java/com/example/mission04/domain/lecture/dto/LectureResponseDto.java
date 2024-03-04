package com.example.mission04.domain.lecture.dto;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.dto.TeacherResponseDto;
import com.example.mission04.domain.teacher.entity.Teacher;
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
}
