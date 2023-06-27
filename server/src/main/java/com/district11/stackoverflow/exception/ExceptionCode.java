package com.district11.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    ANSWER_VOTE_NOT_FOUND(404, "Answer Vote not found"),
    TAG_NOT_FOUND(404, "Tag not found"),
    VOTE_EXISTS(409, "Vote exists"),
    AUTHORITY_NOT_FOUND(404, "authority not found"),
    BAD_REQUEST(400, "bad request"),
    IMAGE_NOT_FOUND(404, "image not found");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
