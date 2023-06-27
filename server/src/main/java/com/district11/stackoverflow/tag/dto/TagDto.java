package com.district11.stackoverflow.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
