package com.example.mission04.domain.teacher.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "teacher_tbl")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer career;

    @Column(nullable = false)
    private String company;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String introduction;

    @Builder
    public Teacher(String name, Integer career, String company, String phone, String introduction) {
        this.name = name;
        this.career = career;
        this.company = company;
        this.phone = phone;
        this.introduction = introduction;
    }
}
