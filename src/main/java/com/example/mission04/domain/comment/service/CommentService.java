package com.example.mission04.domain.comment.service;

import com.example.mission04.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.example.mission04.domain.comment.dto.CommentRequestDto.EditCommentRequestDto;
import com.example.mission04.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.example.mission04.domain.comment.dto.CommentResponseDto.EditCommentResponseDto;
import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.comment.repository.CommentRepository;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, LectureRepository lectureRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public CreateCommentResponseDto create(Long lectureId, String email, CreateCommentRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage())
        );
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new CustomApiException(ErrorCode.LECTURE_ID_NOT_FOUND.getMessage())
        );

        Comment comment = commentRepository.save(requestDto.toEntity(member, lecture));
        return new CreateCommentResponseDto(comment);
    }

    @Transactional
    public EditCommentResponseDto edit(Long lectureId, Long commentId, String email, EditCommentRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage())
        );
        if (!lectureRepository.existsById(lectureId)) {
            throw new CustomApiException(ErrorCode.LECTURE_ID_NOT_FOUND.getMessage());
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CustomApiException(ErrorCode.COMMENT_ID_NOT_FOUND.getMessage())
        );
        if (comment.getMember() != member) {
            throw new CustomApiException(ErrorCode.NOT_MATCH_MEMBER_ACCOUNT.getMessage());
        }

        comment.update(requestDto.getContents());
        return new EditCommentResponseDto(comment);
    }

    @Transactional
    public void delete(Long lectureId, Long commentId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage())
        );
        if (!lectureRepository.existsById(lectureId)) {
            throw new CustomApiException(ErrorCode.LECTURE_ID_NOT_FOUND.getMessage());
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CustomApiException(ErrorCode.COMMENT_ID_NOT_FOUND.getMessage())
        );
        if (comment.getMember() != member) {
            throw new CustomApiException(ErrorCode.NOT_MATCH_MEMBER_ACCOUNT.getMessage());
        }

        commentRepository.delete(comment);
    }
}
