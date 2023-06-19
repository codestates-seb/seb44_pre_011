package com.district11.stackoverflow.tag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OneTagResponseDto {
    private long questionId;
    private String title;
    private String content;
    private String displayName;
    private LocalDateTime creationDate;
    private long memberId;
    private List<TagDto.TagQuestionResponseDto> tags;
}

