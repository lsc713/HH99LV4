package com.example.mission04.domain.member.service;

import com.example.mission04.domain.member.dto.MemberRequestDto.SignupRequestDto;
import com.example.mission04.domain.member.dto.MemberResponseDto.SignupResponseDto;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomApiException(ErrorCode.EMAIL_ALREADY_EXISTS.getMessage());
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = memberRepository.save(requestDto.toEntity(encodedPassword));
        return new SignupResponseDto(member);
    }
}
