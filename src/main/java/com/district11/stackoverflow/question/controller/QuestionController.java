package com.district11.stackoverflow.question.controller;


import com.district11.stackoverflow.dto.MultiResponseDto;
import com.district11.stackoverflow.dto.SingleResponseDto;
import com.district11.stackoverflow.question.dto.QuestionDto;
import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.mapper.QuestionMapper;
import com.district11.stackoverflow.question.service.QuestionService;
import com.district11.stackoverflow.utils.UriCreator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {

    private final static String QUESTION_DEFAULT_URL = "/questions";

    private QuestionMapper mapper;

    private QuestionService questionService;

    public QuestionController(QuestionMapper mapper, QuestionService questionService) {
        this.mapper = mapper;
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto) {
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}") //질문 수정
    public ResponseEntity patchQuestion(@PathVariable("question-id") long questionId,
                                        @Valid @RequestBody QuestionDto.Patch questionPatchDto) {
        questionPatchDto.setQuestionId(questionId);
        Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)), HttpStatus.OK);
    }

    @GetMapping("/{question-id}") //질문 조회
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId){
        Question question = questionService.findQuestion(questionId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(@RequestParam int page, @RequestParam int size){

        Page<Question> pageQuestions = questionService.findQuestions(page-1,size);

        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.questionToQuestionResponseDtos(questions),pageQuestions),HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}") //질문 삭제
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
