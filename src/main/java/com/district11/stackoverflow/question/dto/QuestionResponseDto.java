package com.district11.stackoverflow.question.dto;

import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionResponseDto {
    private long memberId;
    private long questionId;
    private String title;
    private String content;
    private String displayName;
    private List<QuestionTagDto> tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
