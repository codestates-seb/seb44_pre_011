package com.district11.stackoverflow.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

public class QuestionDto {
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Post {

        @NotNull
        private long memberId;
        @NotNull
        private String title;
        @NotNull
        private String content;
        @Size(min = 1, max = 5)
        private List<String> tags;

        public Post(long memberId, String title, String content) {
            this.memberId = memberId;
            this.title = title;
            this.content = content;
        }
    }
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Patch {

        private String title;

        private String content;

        private long questionId;

        public Patch(String title, String content, long questionId) {
            this.title = title;
            this.content = content;
            this.questionId = questionId;
        }
    }
}