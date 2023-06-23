package com.district11.stackoverflow.answer.mapper;

import com.district11.stackoverflow.answer.dto.AnswerPatchDto;
import com.district11.stackoverflow.answer.dto.AnswerPostDto;
import com.district11.stackoverflow.answer.dto.AnswerResponseDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")      // @Mapper : mapper 자동으로 생성해줌
public interface AnswerMapper {         // mapper : SQL을 호출하기 위한 인터페이스

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "questionId", target = "question.questionId")
    @Mapping(source = "displayName", target = "member.displayName")
    Answer AnswerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer AnswerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "question.questionId", target = "questionId")
    @Mapping(source = "member.displayName", target = "displayName")
    AnswerResponseDto AnswerToAnswerResponseDto(Answer answer);

    List<AnswerResponseDto> AnswerToAnswerResponseDtos(List<Answer> answers);


}
