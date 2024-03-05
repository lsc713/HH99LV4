package com.example.mission04.global.handler.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS("이미 가입된 이메일입니다."),
    PHONE_ALREADY_EXISTS("이미 가입된 전화번호입니다."),
    MEMBER_ACCOUNT_NOT_FOUND("찾을 수 없는 계정입니다."),
    COMMENT_ID_NOT_FOUND("찾을 수 없는 댓글 번호입니다."),
    REPLY_ID_NOT_FOUND("찾을 수 없는 대댓글 번호입니다."),
    LECTURE_ID_NOT_FOUND("찾을 수 없는 강의 번호입니다."),
    TEACHER_ID_NOT_FOUND("찾을 수 없는 강사 번호입니다."),
    NOT_MATCH_MEMBER_ACCOUNT("일치하지 않는 계정입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
