package com.district11.stackoverflow.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AnswerPostDto {

    @Positive
    private long memberId;

    @Setter
    private long questionId;

    @NotBlank(message = "내용을 작성해주세요.")
    private String content;

}
