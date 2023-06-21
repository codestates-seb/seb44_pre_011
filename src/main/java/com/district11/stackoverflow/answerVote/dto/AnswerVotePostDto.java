package com.district11.stackoverflow.answerVote.dto;

import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerVotePostDto {

    private long memberId;
    private long answerId;

    private AnswerVote.AnswerVoteStatus answerVoteStatus;

}
