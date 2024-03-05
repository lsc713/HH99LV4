package com.example.mission04.domain.lecture.service;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.example.mission04.domain.teacher.repository.TeacherRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
