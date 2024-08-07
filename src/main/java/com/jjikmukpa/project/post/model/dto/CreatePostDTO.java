package com.jjikmukpa.project.post.model.dto;

import com.jjikmukpa.project.member.model.entity.Member;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreatePostDTO {

    private String postNo;
    private String postTitle;
    private LocalDateTime postDate;
    private String content;
    private Member member;


}
