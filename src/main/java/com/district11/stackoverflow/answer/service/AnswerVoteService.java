package com.district11.stackoverflow.answer.service;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.entity.AnswerVote;
import com.district11.stackoverflow.answer.repository.AnswerVoteRepository;
import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;

    private final AnswerService answerService;

    public AnswerVoteService(AnswerVoteRepository answerVoteRepository, AnswerService answerService) {
        this.answerVoteRepository = answerVoteRepository;
        this.answerService = answerService;
    }

    public AnswerVote AddAnswerVote(AnswerVote answerVote) {

        // 답변이 있는지 검증
        verifyAnswerVote(answerVote);

        return answerVoteRepository.save(answerVote);
    }

    /*
    public AnswerVote updateAnswerVote(AnswerVote answerVote) {

        AnswerVote findAnswerVote = findVerifyAnswerVote(answerVote.getAnswerVoteId());

        Optional.ofNullable(answerVote.getAnswerVoteStatus())
                .ifPresent(answerVoteStatus -> findAnswerVote.setAnswerVoteStatus(answerVoteStatus));

        return answerVoteRepository.save(answerVote);
    }
    */

    public AnswerVote findVerifyAnswerVote(long answerId) {
        Optional<AnswerVote> optionalAnswer = answerVoteRepository.findById(answerId);      // Optional : Null값 허용

        AnswerVote findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_VOTE_NOT_FOUND));

        return findAnswer;
    }

    public void verifyAnswerVote(AnswerVote answerVote) {

        // Answer가 존재하는지 확인
        answerService.findVerifyAnswer(answerVote.getAnswer().getAnswerId());
    }

}
