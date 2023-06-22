package com.district11.stackoverflow.answerVote.dto;

import com.district11.stackoverflow.answerVote.entity.AnswerVote;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerVotePatchDto {

    private long memberId;
    private long answerId;
    private long answerVoteId;

    private AnswerVote.AnswerVoteStatus answerVoteStatus;

}
