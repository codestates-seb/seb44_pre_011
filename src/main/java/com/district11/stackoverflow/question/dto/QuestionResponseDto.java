package com.district11.stackoverflow.question.dto;

import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {
    private long memberId;
    private long questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void setMember(Member member) {
        this.memberId = member.getMemberId();
    }
}
