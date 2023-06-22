package com.district11.stackoverflow.answerVote.service;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.repository.AnswerRepository;
import com.district11.stackoverflow.answer.service.AnswerService;
import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import com.district11.stackoverflow.answerVote.repository.AnswerVoteRepository;
import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.repository.MemberRepository;
import com.district11.stackoverflow.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final AnswerService answerService;
    private final MemberService memberService;

    public AnswerVoteService(AnswerVoteRepository answerVoteRepository, AnswerRepository answerRepository,
                             MemberRepository memberRepository, AnswerService answerService, MemberService memberService) {
        this.answerVoteRepository = answerVoteRepository;
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
        this.answerService = answerService;
        this.memberService = memberService;
    }

    /*
    public AnswerVote addAnswerVote(AnswerVote answerVote){


        Answer findAnswer = findVerifyAnswer(answerVote.getAnswer().getAnswerId());

        answerVoteRepository.findByAnswer(findAnswer).ifPresent(it -> {
            throw new BusinessLogicException(ExceptionCode.VOTE_EXISTS);
        });

        findAnswer.addAnswerVote(answerVote);

        return answerVoteRepository.save(answerVote);
    }

    public void updateAnswerVote(AnswerVote answerVote) {

        AnswerVote findAnswerVote = findVerifiedAnswerVote(answerVote.getAnswerVoteId());

        long verifyMemberId = findAnswerVote.getMember().getMemberId();

        if (answerVote.getMember().getMemberId() != verifyMemberId) {
            throw new BusinessLogicException(ExceptionCode.AUTHORITY_NOT_FOUND);
        }

        Answer answer = findAnswerVote.getAnswer();

        if (findAnswerVote.getAnswerVoteStatus() == answerVote.getAnswerVoteStatus()) {        //기존 VoteStatus 을 재클릭 시 vote 삭제
            answer.removeAnswerVote(findAnswerVote);
            removeAnswerVote(findAnswerVote.getAnswerVoteId());
        } else {                                               //다른 VoteStatus 클릭 시 VoteStatus 변경 후 저장(answer score 최신화)
            findAnswerVote.setAnswerVoteStatus(answerVote.getAnswerVoteStatus());
            answer.updateScore();
            answerVoteRepository.save(findAnswerVote);
        }

    }

    public void removeAnswerVote(long answerVoteId) {
        answerVoteRepository.deleteById(answerVoteId);
    }

     */

    @Transactional(readOnly = true)
    public Answer findVerifyAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);      // Optional : Null값 허용

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }

    public AnswerVote findVerifiedAnswerVote(long answerVoteId) {

        Optional<AnswerVote> optionalAnswerVote = answerVoteRepository.findById(answerVoteId);

        return optionalAnswerVote.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_VOTE_NOT_FOUND));
    }

}
