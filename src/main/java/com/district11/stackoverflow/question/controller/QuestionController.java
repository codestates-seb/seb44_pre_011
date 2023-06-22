package com.district11.stackoverflow.question.controller;


import com.district11.stackoverflow.answer.dto.AnswerResponseDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.dto.MultiResponseDto;
import com.district11.stackoverflow.dto.SingleResponseDto;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.service.MemberService;
import com.district11.stackoverflow.question.dto.QuestionDto;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.mapper.QuestionMapper;
import com.district11.stackoverflow.question.service.QuestionService;
import com.district11.stackoverflow.utils.UriCreator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@Validated
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    private final static String QUESTION_DEFAULT_URL = "/questions";

    private QuestionMapper mapper;

    private QuestionService questionService;

    private MemberService memberService;

    public QuestionController(QuestionMapper mapper, QuestionService questionService, MemberService memberService) {
        this.mapper = mapper;
        this.questionService = questionService;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto) {

        memberService.findMember(questionPostDto.getMemberId());
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

    @GetMapping("/member/{member-id}") //질문 조회
    public ResponseEntity getQuestionByMemberId(@PathVariable("member-id") long memberId){
        List<QuestionResponseDto> response = questionService.findQuestionsByMemberId(memberId);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getQuestions() {
        List<QuestionResponseDto> response = questionService.findQuestions();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity getQuestions(@RequestParam int page, @RequestParam int size){
//
//        Page<Question> pageQuestions = questionService.findQuestions(page-1,size);
//
//        List<Question> questions = pageQuestions.getContent();
//
//        return new ResponseEntity<>(new MultiResponseDto<>(mapper.questionToQuestionResponseDtos(questions),pageQuestions),HttpStatus.OK);
//    }

    @DeleteMapping("/{question-id}") //질문 삭제
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Vote 기능
    @PostMapping("/voteUp/{question-id}/{member-id}")
    public ResponseEntity<?> voteQuestionUp(@PathVariable("question-id") long questionId,
                                            @PathVariable("member-id") long memberId) {

        Question voteQuestionUp = questionService.questionVoteUp(questionId,memberId);
        QuestionResponseDto response = mapper.questionToQuestionResponseDto(voteQuestionUp);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/voteDown/{question-id}/{member-id}")
    public ResponseEntity<?> voteQuestionDown(@PathVariable("question-id") long questionId,
                                              @PathVariable("member-id") long memberId) {
        memberService.findMember(memberId);
        Question voteQuestionDown = questionService.questionVoteDown(questionId,memberId);
        QuestionResponseDto response = mapper.questionToQuestionResponseDto(voteQuestionDown);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
