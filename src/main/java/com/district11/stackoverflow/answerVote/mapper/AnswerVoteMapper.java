package com.district11.stackoverflow.answerVote.mapper;

import com.district11.stackoverflow.answerVote.dto.AnswerVotePatchDto;
import com.district11.stackoverflow.answerVote.dto.AnswerVotePostDto;
import com.district11.stackoverflow.answerVote.dto.AnswerVoteResponseDto;
import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "answerId", target = "answer.answerId")
    AnswerVote AnswerVotePostDtoToAnswer(AnswerVotePostDto answerVotePostDto);

    AnswerVote AnswerVotePatchDtoToAnswer(AnswerVotePatchDto answerVotePatchDto);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "answer.answerId", target = "answerId")
    AnswerVoteResponseDto AnswerToAnswerVoteResponseDto(AnswerVote answerVote);

}
