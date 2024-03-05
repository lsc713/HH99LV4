package com.example.mission04.domain.like.controller;

import com.example.mission04.domain.like.dto.LikeResponseDto;
import com.example.mission04.domain.like.service.LikeService;
import com.example.mission04.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/likes")
@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    @PostMapping("/{lectureId}/like")
    public ResponseEntity pushLike(@PathVariable Long lectureId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.createLike(userDetails,lectureId));
    }
}
