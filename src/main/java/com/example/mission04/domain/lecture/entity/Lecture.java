package com.example.mission04.domain.lecture.entity;

import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.example.mission04.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "lecture_tbl")
public class Lecture extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    private final List<Comment> commentList = new ArrayList<>();

    @Builder
    public Lecture(String name, Integer price, String introduction, CategoryType category, Teacher teacher) {
        this.name = name;
        this.price = price;
        this.introduction = introduction;
        this.category = category;
        this.teacher = teacher;
    }
}
