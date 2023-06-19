package com.district11.stackoverflow.member.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String displayName;
        private LocalDateTime modifiedAt;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private Long memberId;

        private String displayName;

        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String email;
        private String displayName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
