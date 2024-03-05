package com.example.mission04.domain.member.controller;

import com.example.mission04.domain.member.dto.MemberRequestDto;
import com.example.mission04.domain.member.dto.MemberResponseDto.SignupResponseDto;
import com.example.mission04.domain.member.service.MemberService;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<SignupResponseDto> signup(@RequestBody @Valid MemberRequestDto.SignupMemberRequestDto requestDto) {
        SignupResponseDto responseDto = memberService.signup(requestDto);
        return ResponseDto.success("회원 가입 기능", responseDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<Void> delete(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        memberService.delete(userDetails.getUsername());
        return ResponseDto.success("회원 탈퇴 기능", null);
    }
}
