package com.jjikmukpa.project.reply.model.dto;

import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.post.model.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReplyDTO {

    private long replyNo;
    private String replyContent;
    private LocalDateTime replyDate;
    private Post post;
    private Member member;
    private boolean editMode;

}

