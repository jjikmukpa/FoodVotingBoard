package com.jjikmukpa.project.reply.repository;

import com.jjikmukpa.project.reply.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByPost_PostNo(long postNo);


    Long findPostNoByReplyNo(Long replyNo);
}
