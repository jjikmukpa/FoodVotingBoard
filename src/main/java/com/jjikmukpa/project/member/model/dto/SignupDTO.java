package com.jjikmukpa.project.member.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignupDTO {
    private int memberCode;
    private String memberId;
    private String memberPw;
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String dateOfBirth;
    private String address;
    private String status = "ACTIVATED";
    private String role = "USER";
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime deletedDate;
    private LocalDateTime suspendedDate;
    private int suspensionDays;
}
