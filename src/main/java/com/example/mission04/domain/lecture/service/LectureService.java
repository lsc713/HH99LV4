package com.example.mission04.domain.lecture.service;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CraeteLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.ReadLectureResponseDto;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mission04.global.handler.exception.ErrorCode.MEMBER_ACCOUNT_NOT_FOUND;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public CraeteLectureResponseDto createLecture(String email, CreateLectureRequestDto requestDto) {
        validateAuthority(email);
        Lecture lecture = lectureRepository.save(requestDto.toEntity());
        return new CraeteLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public ReadLectureResponseDto readLecture(Long id) {
        return new ReadLectureResponseDto(lectureRepository.findById(id).orElseThrow(() -> new CustomApiException(MEMBER_ACCOUNT_NOT_FOUND.getMessage())));
    }


    @Transactional(readOnly = true)
    public Page<ReadLectureResponseDto> readLectureByCategory(String category, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Lecture> lectures = lectureRepository.findByCategory(category,pageable);
        return lectures.map(ReadLectureResponseDto::new);
    }

    private void validateAuthority(String email) {
        if (!lectureRepository.findByEmailExists(email)) {
            throw new CustomApiException(MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
    }
}
