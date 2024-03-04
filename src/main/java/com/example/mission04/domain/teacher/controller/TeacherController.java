package com.example.mission04.domain.teacher.controller;

import com.example.mission04.domain.member.entity.type.AuthorityType.Authority;
import com.example.mission04.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission04.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission04.domain.teacher.service.TeacherService;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/teachers")
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    @Secured(Authority.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateTeacherResponseDto> create(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateTeacherRequestDto requestDto
    ) {
        CreateTeacherResponseDto responseDto = teacherService.create(userDetails.getUsername(), requestDto);
        return ResponseDto.success("강사 등록 기능", responseDto);
    }
}
