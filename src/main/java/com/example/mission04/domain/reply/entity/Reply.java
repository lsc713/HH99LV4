package com.example.mission04.domain.reply.entity;

import com.example.mission04.domain.comment.entity.Comment;
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
@Table(name = "reply_tbl")
@SQLDelete(sql = "UPDATE reply_tbl SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Reply extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    private final boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Reply(String contents, Member member, Comment comment) {
        this.contents = contents;
        this.member = member;
        this.comment = comment;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
