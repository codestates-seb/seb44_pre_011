package com.district11.stackoverflow.answer.controller;

import com.district11.stackoverflow.answer.dto.AnswerPatchDto;
import com.district11.stackoverflow.answer.dto.AnswerPostDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.mapper.AnswerMapper;
import com.district11.stackoverflow.answer.service.AnswerService;
import com.district11.stackoverflow.dto.SingleResponseDto;
import com.district11.stackoverflow.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {

    private final static String ANSWER_DEFAULT_URL = "/answers";

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @PostMapping("/{question-id}")
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto,
                                        @Positive @PathVariable("question-id") long questionId) {

        answerPostDto.setQuestionId(questionId);
        Answer answer = answerService.createAnswer(answerMapper.AnswerPostDtoToAnswer(answerPostDto));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}/{answer-id}")
    public ResponseEntity<?> patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody AnswerPatchDto answerPatchDto) {

        answerPatchDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(answerMapper.AnswerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(answerMapper.AnswerToAnswerResponseDto(answer)), HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}/{answer-id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
