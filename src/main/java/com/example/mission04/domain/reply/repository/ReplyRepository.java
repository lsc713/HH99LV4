package com.example.mission04.domain.reply.repository;

import com.example.mission04.domain.comment.entity.Comment;
import com.example.mission04.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByCommentOrderByCreatedAtDesc(Comment comment);
}
