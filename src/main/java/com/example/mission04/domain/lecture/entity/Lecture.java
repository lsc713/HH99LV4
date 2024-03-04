package com.example.mission04.domain.lecture.entity;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.*;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.example.mission04.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "lecture_tbl")
public class Lecture extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;
    private String lectureIntroduce;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Builder
    public Lecture(String name, Integer price, CategoryType category, Teacher teacher) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.teacher = teacher;
    }

    @Builder
    public Lecture(CreateLectureRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.lectureIntroduce = requestDto.getLectureIntroduce();
        this.category = requestDto.getCategory();
        this.teacher = requestDto.getTeacher();
    }
}
