package com.district11.stackoverflow.answer.mapper;

import com.district11.stackoverflow.answer.dto.AnswerVotePostDto;
import com.district11.stackoverflow.answer.entity.AnswerVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "answerId", target = "answer.answerId")
    AnswerVote AnswerVotePostDtoToAnswer(AnswerVotePostDto answerVotePostDto);

}
