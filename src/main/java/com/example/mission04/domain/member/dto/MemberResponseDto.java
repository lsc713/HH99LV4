package com.example.mission04.domain.member.dto;

import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.entity.type.AuthorityType;
import com.example.mission04.domain.member.entity.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberResponseDto {

    @AllArgsConstructor
    @Getter
    public static class SignupResponseDto {

        private String email;
        private String phone;
        private GenderType gender;
        private String address;
        private AuthorityType authority;

        public SignupResponseDto(Member member) {
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.gender = member.getGender();
            this.address = member.getAddress();
            this.authority = member.getAuthority();
        }
    }
}
