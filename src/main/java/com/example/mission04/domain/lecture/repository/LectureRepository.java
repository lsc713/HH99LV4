package com.example.mission04.domain.lecture.repository;

import com.example.mission04.domain.lecture.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    boolean findByEmailExists(String email);

    Page<Lecture> findByCategory(String category, Pageable pageable);
}
