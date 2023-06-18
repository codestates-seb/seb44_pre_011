package com.district11.stackoverflow.answer.dto;

import com.district11.stackoverflow.answer.entity.AnswerVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class AnswerVotePostDto {

    @Positive
    private long memberId;

    @Setter
    private long answerId;

    private AnswerVote.AnswerVoteStatus answerVoteStatus;

}
