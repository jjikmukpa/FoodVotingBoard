package com.jjikmukpa.project.post.model.entity;

import com.jjikmukpa.project.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private long postCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code", nullable = false)
    private Member member;

    private String content;

    private String blindStatus;
}
