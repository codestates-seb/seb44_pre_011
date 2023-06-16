package com.district11.stackoverflow.answer.controller;

import com.district11.stackoverflow.answer.dto.AnswerPatchDto;
import com.district11.stackoverflow.answer.dto.AnswerPostDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.mapper.AnswerMapper;
import com.district11.stackoverflow.answer.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {

    private final static String ORDER_DEFAULT_URL = "/answers";

    private AnswerService answerService;
    private AnswerMapper answerMapper;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @PostMapping
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto) {

        Answer answer = answerService.createAnswer(answerMapper.AnswerPostDtoToAnswer(answerPostDto));

        return new ResponseEntity<>(answerMapper.AnswerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody AnswerPatchDto answerPatchDto) {

        answerPatchDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(answerMapper.AnswerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(answerMapper.AnswerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
