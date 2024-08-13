package com.jjikmukpa.project.post.model.entity;

import com.jjikmukpa.project.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "debatePost")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DebatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postNo;

    private String postTitle;

    private LocalDateTime createdDate;


    private long postCount ; // 조회수


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code", nullable = false)
    private Member member;

    private String content;

    private String blindStatus;

    // 조회수를 증가시키는 메소드 추가
    public void incrementPostCount() {
        this.postCount++;
    }

}
