package com.district11.stackoverflow.answer.service;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.entity.AnswerVote;
import com.district11.stackoverflow.answer.repository.AnswerVoteRepository;
import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;
    private final AnswerService answerService;
    private final MemberService memberService;

    public AnswerVoteService(AnswerVoteRepository answerVoteRepository, AnswerService answerService, MemberService memberService) {
        this.answerVoteRepository = answerVoteRepository;
        this.answerService = answerService;
        this.memberService = memberService;
    }

    public AnswerVote addAnswerVote(AnswerVote answerVote) {

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

        // member가 존재하는지 확인
        Member member = memberService.findMember(answerVote.getMember().getMemberId());
        answerVote.setMember(member);

        // Answer가 존재하는지 확인
        Answer answer = answerService.findVerifyAnswer(answerVote.getAnswer().getAnswerId());
        answerVote.setAnswer(answer);
    }

}
