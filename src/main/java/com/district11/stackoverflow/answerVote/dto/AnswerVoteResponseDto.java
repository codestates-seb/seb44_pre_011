package com.district11.stackoverflow.answerVote.dto;

import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerVoteResponseDto {

    private long memberId;
    private long answerId;
    private long answerVoteId;
    public long voteCount;

    private AnswerVote.AnswerVoteStatus answerVoteStatus;

}
