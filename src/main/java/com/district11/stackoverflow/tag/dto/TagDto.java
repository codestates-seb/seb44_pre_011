package com.district11.stackoverflow.tag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class TagDto {
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Post {
        private String name;
        private String info;
    }

    public static class Patch {
        private String tagId;
        private String name;
        private String info;
    }

    public static class Response {
        private String tagId;
        private String name;
        private String info;
    }
}
