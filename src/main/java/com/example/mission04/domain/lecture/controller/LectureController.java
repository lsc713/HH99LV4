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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "lectures", description = "강의 관련 API")
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
    @Operation(summary = "강의 등록 기능", description = "강의를 등록할 수 있는 API")
    public ResponseDto<CreateLectureResponseDto> create(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateLectureRequestDto requestDto
    ) {
        CreateLectureResponseDto responseDto = lectureService.create(userDetails.getUsername(), requestDto);
        return ResponseDto.success("강의 등록 기능", responseDto);
    }

    @GetMapping("/{lectureId}")
    @Operation(summary = "강의 조회 기능", description = "강의를 조회할 수 있는 API")
    public ResponseDto<GetLectureResponseDto> get(@PathVariable Long lectureId) {
        GetLectureResponseDto responseDto = lectureService.get(lectureId);
        return ResponseDto.success("강의 조회 기능", responseDto);
    }

    @GetMapping("/search")
    @Operation(summary = "카테고리별 강의 목록 조회 기능", description = "카테고리별로 강의를 조회할 수 있는 API")
    public ResponseDto<List<SearchLectureResponseDto>> search(
            @RequestParam CategoryType category,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder
    ) {
        List<SearchLectureResponseDto> responseDto = lectureService.search(category, sortBy, sortOrder);
        return ResponseDto.success("카테고리별 강의 목록 조회 기능", responseDto);
    }
}
