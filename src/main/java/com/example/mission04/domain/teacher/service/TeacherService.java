package com.example.mission04.domain.teacher.service;

import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission04.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission04.domain.teacher.entity.Teacher;
import com.example.mission04.domain.teacher.repository.TeacherRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MemberRepository memberRepository;

    public TeacherService(TeacherRepository teacherRepository, MemberRepository memberRepository) {
        this.teacherRepository = teacherRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public CreateTeacherResponseDto create(String email, CreateTeacherRequestDto requestDto) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
        if (teacherRepository.existsByPhone(requestDto.getPhone())) {
            throw new CustomApiException(ErrorCode.PHONE_ALREADY_EXISTS.getMessage());
        }

        Teacher teacher = teacherRepository.save(requestDto.toEntity());
        return new CreateTeacherResponseDto(teacher);
    }
}
