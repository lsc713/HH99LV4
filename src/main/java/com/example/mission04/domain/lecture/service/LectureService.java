package com.example.mission04.domain.lecture.service;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.SearchLectureResponseDto;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.domain.lecture.strategy.SortStrategy;
import com.example.mission04.domain.lecture.strategy.SortStrategyFactory;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.example.mission04.domain.teacher.repository.TeacherRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final MemberRepository memberRepository;
    private final TeacherRepository teacherRepository;

    public LectureService(LectureRepository lectureRepository, MemberRepository memberRepository, TeacherRepository teacherRepository) {
        this.lectureRepository = lectureRepository;
        this.memberRepository = memberRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public CreateLectureResponseDto create(String email, CreateLectureRequestDto requestDto) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId()).orElseThrow(() ->
                new CustomApiException(ErrorCode.TEACHER_ID_NOT_FOUND.getMessage())
        );

        Lecture lecture = lectureRepository.save(requestDto.toEntity(teacher));
        return new CreateLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public GetLectureResponseDto get(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new CustomApiException(ErrorCode.LECTURE_ID_NOT_FOUND.getMessage())
        );

        return new GetLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public List<SearchLectureResponseDto> search(CategoryType category, String sortBy, String sortOrder) {
        List<Lecture> lectureList = lectureRepository.findAllByCategory(category);

        SortStrategy sortStrategy = SortStrategyFactory.getSortStrategy(sortBy);
        sortStrategy.sort(lectureList);

        if (sortOrder.equals("desc")) {
            Collections.reverse(lectureList);
        }
        return lectureList.stream()
                .map(SearchLectureResponseDto::new)
                .toList();
    }
}
