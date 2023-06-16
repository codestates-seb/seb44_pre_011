package com.district11.stackoverflow.question.service;

import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.repository.QuestionRepository;

public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }


}
