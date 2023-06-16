package com.district11.stackoverflow.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AnswerPostDto {

    @Positive
    private long memberId;

    @Setter
    private long questionId;

    @NotBlank(message = "공백이 아니어야 합니다.")
    private String content;

}
