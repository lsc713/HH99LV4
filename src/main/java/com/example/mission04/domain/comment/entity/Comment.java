package com.example.mission04.domain.comment.entity;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comment_tbl")
@SQLDelete(sql = "UPDATE comment_tbl SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Builder
    public Comment(String contents, boolean deleted, Member member, Lecture lecture) {
        this.contents = contents;
        this.deleted = deleted;
        this.member = member;
        this.lecture = lecture;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
