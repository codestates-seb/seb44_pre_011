package com.district11.stackoverflow.answer.mapper;

import com.district11.stackoverflow.answer.dto.AnswerVotePostDto;
import com.district11.stackoverflow.answer.entity.AnswerVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {

    AnswerVote AnswerVotePostDtoToAnswer(AnswerVotePostDto answerVotePostDto);

}
