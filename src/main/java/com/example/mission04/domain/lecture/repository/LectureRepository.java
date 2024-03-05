package com.example.mission04.domain.lecture.repository;

import com.example.mission04.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    boolean existsByEmail(String email);

    List<Lecture> findByCategory(String category);
}
