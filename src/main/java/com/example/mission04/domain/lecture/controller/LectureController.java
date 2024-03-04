package com.example.mission04.domain.lecture.controller;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CraeteLectureResponseDto;
import com.example.mission04.domain.lecture.service.LectureService;
import com.example.mission04.domain.member.entity.type.AuthorityType.Authority;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/lectures")
@RestController
@AllArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/{id}")
    @Secured(Authority.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CraeteLectureResponseDto> createLecture(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                  @RequestBody @Valid CreateLectureRequestDto requestDto, BindingResult bindingResult) {

        return ResponseEntity.status(HttpStatus.CREATED).body(lectureService.createLecture(userDetails.getUsername(), requestDto));
    }

}
