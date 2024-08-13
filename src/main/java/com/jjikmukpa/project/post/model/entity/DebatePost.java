package com.jjikmukpa.project.post.model.entity;

import com.jjikmukpa.project.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import com.jjikmukpa.project.post.model.Option;

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

    private long postCount; // 조회수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code", nullable = false)
    private Member member;

    private String content;

    private String blindStatus;

    private String image1Path;
    private String image2Path;

    public void addOption(Option option) {
        this.image1Path = option.getImage1();
        this.image2Path = option.getImage2();
    }

    // 조회수를 증가시키는 메소드 추가
    public void incrementPostCount() {
        this.postCount++;
    }

    // 초깃값 설정을 위한 메소드
    public void initializeViewCount() {
        this.postCount = 0;
    }

}
