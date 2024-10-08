package com.jjikmukpa.project.member.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberCode;

    private String memberId;
    private String memberPw;
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String address;
    private String dateOfBirth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime deletedDate;
    private LocalDateTime suspendedDate;
    private int suspensionDays;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVATED;

    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.USER;
}
