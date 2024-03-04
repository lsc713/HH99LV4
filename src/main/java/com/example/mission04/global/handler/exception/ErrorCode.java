package com.example.mission04.global.handler.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS("이미 가입된 이메일입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
