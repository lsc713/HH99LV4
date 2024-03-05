package com.example.mission04.domain.lecture.entity;

import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.example.mission04.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

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

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @Builder
    public Lecture(String name, Integer price, String lectureIntroduce, CategoryType category, Teacher teacher, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.lectureIntroduce = lectureIntroduce;
        this.category = category;
        this.teacher = teacher;
        this.createdAt = createdAt;
    }


}
