package com.district11.stackoverflow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuestionResponseDto {
    private long memberId;
    private long questionId;
    private String title;
    private String content;
    private String displayName;
    private long questionVoteCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
