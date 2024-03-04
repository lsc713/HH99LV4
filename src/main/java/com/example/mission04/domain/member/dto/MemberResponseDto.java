package com.example.mission04.domain.member.dto;

import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.entity.type.AuthorityType;
import com.example.mission04.domain.member.entity.type.GenderType;
import lombok.Getter;

public class MemberResponseDto {

    @Getter
    public static class SignupResponseDto {

        private final String email;
        private final String phone;
        private final GenderType gender;
        private final String address;
        private final AuthorityType authority;

        public SignupResponseDto(Member member) {
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.gender = member.getGender();
            this.address = member.getAddress();
            this.authority = member.getAuthority();
        }
    }

    @Getter
    public static class SigninMemberResponseDto {

        private final String username;

        public SigninMemberResponseDto(Member member) {
            this.username = member.getEmail();
        }
    }
}
