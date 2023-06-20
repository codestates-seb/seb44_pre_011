package com.district11.stackoverflow.question.service;

import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.mapper.QuestionMapper;
import com.district11.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Question createQuestion(Question question){
        //todo: Tags 서비스 로직 추가해야함

        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question){
        Question findQuestion = findQuestion(question.getQuestionId());
        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(findQuestion::setContent);

        return findQuestion;
    }
    public void deleteQuestion(long questionId){
        Question question = findQuestion(questionId);
        questionRepository.deleteById(questionId);
    }
    public Question findQuestion(long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = optionalQuestion.orElseThrow(()->new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(PageRequest.of(page,size, Sort.by("questionId").descending()));
    }


    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }
}