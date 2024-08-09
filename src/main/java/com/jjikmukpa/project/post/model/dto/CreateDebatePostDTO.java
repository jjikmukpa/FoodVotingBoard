package com.jjikmukpa.project.post.model.dto;

import com.jjikmukpa.project.member.model.entity.Member;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateDebatePostDTO {

    private String debatePostNo;
    private String debatePostTitle;
    private LocalDateTime debatePostDate;
    private String content;
    private Member member;


}
