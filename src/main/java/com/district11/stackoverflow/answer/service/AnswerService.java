package com.district11.stackoverflow.answer.service;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.repository.AnswerRepository;
import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.service.MemberService;
import com.district11.stackoverflow.question.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, MemberService memberService, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.questionService = questionService;
    }

    public Answer createAnswer(Answer answer) {

        VerifyAnswer(answer);       // 답변을 할 수 있는지 검증

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = findVerifyAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getContent())                            // ofNullable : 일반 객체뿐만 아니라 null 값까지 받을 수 있다.
                .ifPresent(content -> findAnswer.setContent(content));

        return answerRepository.save(findAnswer);
    }


    public void deleteAnswer(long answerId) {

        Answer findAnswer = findVerifyAnswer(answerId);
        findAnswer.setAnswerStatus(Answer.AnswerStatus.ANSWER_DELETE);
        answerRepository.save(findAnswer);
    }

    // Answer를 수정하기 위해선 Answer가 있는지 검증
    public Answer findVerifyAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }


    // createAnswer 하기위해서 필요
    private void VerifyAnswer(Answer answer) {

        // member가 존재하는지 확인
        memberService.findMember(answer.getMember().getMemberId());

        // 질문이 존재하는지 확인
        questionService.findQuestion(answer.getQuestion().getQuestionId());
    }

}
