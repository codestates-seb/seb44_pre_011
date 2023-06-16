package com.district11.stackoverflow.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

        public Post(long member_id, String title, String content) {
            this.memberId = member_id;
            this.title = title;
            this.content = content;
        }
    }
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Patch {
        private long memberId;

        private String title;

        private String content;

        private long questionId;

        public Patch(long member_id, String title, String content, long questionId) {
            this.memberId = member_id;
            this.title = title;
            this.content = content;
            this.questionId = questionId;
        }
    }
}