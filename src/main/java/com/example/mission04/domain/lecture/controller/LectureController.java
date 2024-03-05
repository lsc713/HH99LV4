package com.example.mission04.domain.lecture.controller;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CraeteLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.ReadLectureResponseDto;
import com.example.mission04.domain.lecture.service.LectureService;
import com.example.mission04.domain.member.entity.type.AuthorityType.Authority;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/lectures")
@RestController
@AllArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    @Secured(Authority.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CraeteLectureResponseDto> createLecture(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                  @RequestBody @Valid CreateLectureRequestDto requestDto, BindingResult bindingResult) {

        return ResponseEntity.status(HttpStatus.CREATED).body(lectureService.createLecture(userDetails.getUsername(), requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadLectureResponseDto> readLecture(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.readLecture(id));
    }

    @GetMapping("/{category}")
    public ResponseEntity<Page<ReadLectureResponseDto>> readLectureByCategory(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(
                lectureService.readLectureByCategory(category, page - 1, size, sortBy, isAsc));
    }


}
