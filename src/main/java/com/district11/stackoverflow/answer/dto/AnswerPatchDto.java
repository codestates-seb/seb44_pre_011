package com.district11.stackoverflow.answer.dto;

import com.district11.stackoverflow.answer.entity.Answer;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AnswerPatchDto {

    @Setter
    private long answerId;

    private Answer.AnswerStatus answerStatus;

    @NotBlank(message = "공백이 아니어야 합니다.")
    private String content;

}
