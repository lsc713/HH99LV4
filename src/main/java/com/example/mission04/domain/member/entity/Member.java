package com.example.mission04.domain.member.entity;

import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.like.entity.Like;
import com.example.mission04.domain.member.entity.type.AuthorityType;
import com.example.mission04.domain.member.entity.type.GenderType;
import com.example.mission04.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member_tbl")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthorityType authority;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    List<Like> likeList = new ArrayList<>();

    @Builder
    public Member(String email, String password, GenderType gender, String phone, String address, AuthorityType authority) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.authority = authority;
    }
}
