package com.example.mission04.domain.lecture.controller;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.SearchLectureResponseDto;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.lecture.service.LectureService;
import com.example.mission04.domain.member.entity.type.AuthorityType.Authority;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/lectures")
@RestController
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(Authority.ADMIN)
    public ResponseDto<CreateLectureResponseDto> create(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateLectureRequestDto requestDto
    ) {
        CreateLectureResponseDto responseDto = lectureService.create(userDetails.getUsername(), requestDto);
        return ResponseDto.success("강의 등록 기능", responseDto);
    }

    @GetMapping("/{lectureId}")
    public ResponseDto<GetLectureResponseDto> get(@PathVariable Long lectureId) {
        GetLectureResponseDto responseDto = lectureService.get(lectureId);
        return ResponseDto.success("강의 조회 기능", responseDto);
    }

    @GetMapping("/search")
    public ResponseDto<List<SearchLectureResponseDto>> search(
            @RequestParam CategoryType category,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder
    ) {
        List<SearchLectureResponseDto> responseDto = lectureService.search(category, sortBy, sortOrder);
        return ResponseDto.success("카테고리별 강의 목록 조회 기능", responseDto);
    }
}
