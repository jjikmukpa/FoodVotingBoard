package com.jjikmukpa.project.post.model.entity;

import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.reply.model.entity.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postNo;

    private String postTitle;

    private LocalDateTime createdDate;

    private LocalDateTime modifyDate;

    private long postCount; // 조회수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code", nullable = false)
    private Member member;

    private String content;

    private String blindStatus;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replies;  // 댓글 목록

}
