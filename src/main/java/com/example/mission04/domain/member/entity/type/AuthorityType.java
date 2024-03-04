package com.example.mission04.domain.member.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityType {

    MEMBER(Authority.MEMBER), ADMIN(Authority.ADMIN);

    private final String authority;

    public static class Authority {
        public static final String MEMBER = "ROLE_MEMBER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
