package com.example.mission04.domain.reply.service;

import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.comment.repository.CommentRepository;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.domain.reply.dto.ReplyRequestDto.CreateReplyRequestDto;
import com.example.mission04.domain.reply.dto.ReplyRequestDto.EditReplyRequestDto;
import com.example.mission04.domain.reply.dto.ReplyResponseDto.CreateReplyResponseDto;
import com.example.mission04.domain.reply.dto.ReplyResponseDto.EditReplyResponseDto;
import com.example.mission04.domain.reply.dto.ReplyResponseDto.GetReplyResponseDto;
import com.example.mission04.domain.reply.entity.Reply;
import com.example.mission04.domain.reply.repository.ReplyRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public ReplyService(ReplyRepository replyRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public CreateReplyResponseDto create(Long commentId, String email, CreateReplyRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage())
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CustomApiException(ErrorCode.COMMENT_ID_NOT_FOUND.getMessage())
        );

        Reply reply = replyRepository.save(requestDto.toEntity(member, comment));
        return new CreateReplyResponseDto(reply);
    }

    @Transactional
    public EditReplyResponseDto edit(Long commentId, Long replyId, String email, EditReplyRequestDto requestDto) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
        if (!commentRepository.existsById(commentId)) {
            throw new CustomApiException(ErrorCode.COMMENT_ID_NOT_FOUND.getMessage());
        }
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new CustomApiException(ErrorCode.REPLY_ID_NOT_FOUND.getMessage())
        );

        reply.update(requestDto.getContents());
        return new EditReplyResponseDto(reply);
    }

    @Transactional
    public void delete(Long commentId, Long replyId, String email) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
        if (!commentRepository.existsById(commentId)) {
            throw new CustomApiException(ErrorCode.COMMENT_ID_NOT_FOUND.getMessage());
        }
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new CustomApiException(ErrorCode.REPLY_ID_NOT_FOUND.getMessage())
        );

        replyRepository.delete(reply);
    }

    @Transactional(readOnly = true)
    public List<GetReplyResponseDto> getRepliesOfComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CustomApiException(ErrorCode.COMMENT_ID_NOT_FOUND.getMessage())
        );

        return replyRepository.findAllByCommentOrderByCreatedAtDesc(comment).stream()
                .map(GetReplyResponseDto::new)
                .toList();
    }
}
