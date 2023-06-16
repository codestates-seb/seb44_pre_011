package com.district11.stackoverflow.question.mapper;

import com.district11.stackoverflow.question.dto.QuestionDto;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper{
    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);
    QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions);
}
