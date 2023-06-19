package com.district11.stackoverflow.tag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class TagDto {
    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class QuestionResponseDto {
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime creationDate;
        private TagUserResponseDto member;
        private List<TagQuestionResponseDto> tags;

    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TagUserResponseDto {
        private long memberId;
        private String displayName;
    }


    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TagQuestionResponseDto {
        private long tagId;
        private String name;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TagQuestionResponseDtos {
        private long tagId;
        private String name;
        private String info;
    }
}
