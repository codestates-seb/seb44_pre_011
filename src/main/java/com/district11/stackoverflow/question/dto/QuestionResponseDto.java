package com.district11.stackoverflow.question.dto;

import com.district11.stackoverflow.member.dto.MemberDto;

import java.time.LocalDateTime;

public class QuestionResponseDto {
    private MemberDto.Response member;
    private long questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
