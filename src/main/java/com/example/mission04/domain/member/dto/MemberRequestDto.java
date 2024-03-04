package com.example.mission04.domain.member.dto;

import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.entity.type.AuthorityType;
import com.example.mission04.domain.member.entity.type.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class MemberRequestDto {

    @Getter
    public static class SignupRequestDto {

        @Email(message = "이메일 형식이 아닙니다.")
        private String email;

        @Pattern(
                message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다.",
                regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$"
        )
        private String password;

        private GenderType gender;

        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;

        @NotBlank(message = "주소를 입력해주세요.")
        private String address;

        private AuthorityType authority;

        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .email(this.email)
                    .password(encodedPassword)
                    .phone(this.phone)
                    .gender(this.gender)
                    .address(this.address)
                    .authority(this.authority)
                    .build();
        }
    }

    @Getter
    public static class SigninMemberRequestDto {

        @NotBlank(message = "이메일을 입력해주세요.")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }
}
