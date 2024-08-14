package com.jjikmukpa.project.post.model.dto;

import com.jjikmukpa.project.member.model.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDTO {

    private long postNo;
    private String postTitle;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private Member member;
    private long postCount;

}
