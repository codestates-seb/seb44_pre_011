package com.district11.stackoverflow.answer.controller;

import com.district11.stackoverflow.answer.dto.AnswerVotePostDto;
import com.district11.stackoverflow.answer.entity.AnswerVote;
import com.district11.stackoverflow.answer.mapper.AnswerVoteMapper;
import com.district11.stackoverflow.answer.service.AnswerVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;
    private final AnswerVoteMapper answerVoteMapper;

    public AnswerVoteController(AnswerVoteService answerVoteService, AnswerVoteMapper answerVoteMapper) {
        this.answerVoteService = answerVoteService;
        this.answerVoteMapper = answerVoteMapper;
    }

    @PostMapping("/{answer-id}/votes")
    public ResponseEntity<?> postAnswerVote(@PathVariable("answer-id") @Positive long answerId,
                                            @Valid @RequestBody AnswerVotePostDto answerVotePostDto) {

        answerVotePostDto.setAnswerId(answerId);
        AnswerVote answerVote = answerVoteMapper.AnswerVotePostDtoToAnswer(answerVotePostDto);
        AnswerVote addAnswerVote = answerVoteService.addAnswerVote(answerVote);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
