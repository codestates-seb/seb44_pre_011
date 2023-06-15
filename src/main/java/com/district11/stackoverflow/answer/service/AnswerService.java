package com.district11.stackoverflow.answer.service;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.repository.AnswerRepository;
import com.district11.stackoverflow.member.repository.MemberRepository;
import com.district11.stackoverflow.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, MemberRepository memberRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
        this.questionRepository = questionRepository;
    }

    public Answer createAnswer(Answer answer) {

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {

        return answerRepository.save(answer);
    }

    /*
    public Answer deleteAnswer() {
        return answerRepository.delete();
    }
    */

}
