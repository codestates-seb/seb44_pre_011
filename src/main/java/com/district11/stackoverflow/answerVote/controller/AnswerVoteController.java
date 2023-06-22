package com.district11.stackoverflow.answerVote.controller;

import com.district11.stackoverflow.answer.dto.AnswerResponseDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answerVote.dto.AnswerVotePatchDto;
import com.district11.stackoverflow.answerVote.dto.AnswerVotePostDto;
import com.district11.stackoverflow.answerVote.dto.AnswerVoteResponseDto;
import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import com.district11.stackoverflow.answerVote.mapper.AnswerVoteMapper;
import com.district11.stackoverflow.answerVote.repository.AnswerVoteRepository;
import com.district11.stackoverflow.answerVote.service.AnswerVoteService;
import com.district11.stackoverflow.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;
    private final AnswerVoteMapper answerVoteMapper;
    private final AnswerVoteRepository answerVoteRepository;

    public AnswerVoteController(AnswerVoteService answerVoteService, AnswerVoteMapper answerVoteMapper,
                                AnswerVoteRepository answerVoteRepository) {
        this.answerVoteService = answerVoteService;
        this.answerVoteMapper = answerVoteMapper;
        this.answerVoteRepository = answerVoteRepository;
    }
    /*
    // 추천기능
    @PostMapping("/upVote/{answer-Id}")
    public ResponseEntity upVoteAnswer(@PathVariable("answer-Id") long answerId) {

        Answer votedAnswerUp = answerService.upVote(answerId);
        AnswerResponseDto response = answerMapper.AnswerToAnswerResponseDto(votedAnswerUp);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/downVote/{answer-id}")
    public ResponseEntity downVoteAnswer(@PathVariable("answer-id") long answerId) {

        Answer votedAnswerDown = answerService.downVote(answerId);
        AnswerResponseDto response = answerMapper.AnswerToAnswerResponseDto(votedAnswerDown);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     */

}
