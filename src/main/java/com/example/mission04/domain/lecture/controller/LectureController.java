package com.example.mission04.domain.lecture.controller;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.ReadLectureResponseDto;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.lecture.service.LectureService;
import com.example.mission04.domain.like.service.LikeService;
import com.example.mission04.domain.member.entity.type.AuthorityType.Authority;
import com.example.mission04.global.dto.ResponseDto;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/lectures")
@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    @Secured(Authority.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateLectureResponseDto> createLecture(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                               @RequestBody @Valid CreateLectureRequestDto requestDto, BindingResult bindingResult) {

        return ResponseDto.success("성공!ㅋㅋ",lectureService.createLecture(userDetails.getUsername(), requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadLectureResponseDto> readLecture(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.readLecture(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ReadLectureResponseDto>> readLectureByCategory(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @PathVariable CategoryType category) {
        return ResponseEntity.status(HttpStatus.OK).body(
                lectureService.readLectureByCategory(category, page - 1, size, sortBy, isAsc));
    }

}
