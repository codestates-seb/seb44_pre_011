package com.district11.stackoverflow.question.mapper;

import com.district11.stackoverflow.question.dto.QuestionDto;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper{
    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);

    QuestionResponseDto questionToQuestionResponseDto(Question question);
}
