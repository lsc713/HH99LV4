package com.example.mission04.domain.lecture.strategy;

import com.example.mission04.domain.lecture.entity.Lecture;

import java.util.Comparator;
import java.util.List;

public class NameSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Lecture> lectureList) {
        lectureList.sort(Comparator.comparing(Lecture::getName));
    }
}
