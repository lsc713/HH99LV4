package com.example.mission04.domain.lecture.strategy;

import com.example.mission04.domain.lecture.entity.Lecture;

import java.util.List;

public interface SortStrategy {

    void sort(List<Lecture> lectureList);
}
