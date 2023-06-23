package com.district11.stackoverflow.answer.controller;

import com.district11.stackoverflow.answer.dto.AnswerPatchDto;
import com.district11.stackoverflow.answer.dto.AnswerPostDto;
import com.district11.stackoverflow.answer.dto.AnswerResponseDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.mapper.AnswerMapper;
import com.district11.stackoverflow.answer.service.AnswerService;
import com.district11.stackoverflow.dto.SingleResponseDto;
import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.service.MemberService;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.service.QuestionService;
import com.district11.stackoverflow.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {

    private final static String ANSWER_DEFAULT_URL = "/answers";

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final QuestionService questionService;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper,
                            MemberService memberService, QuestionService questionService) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.memberService = memberService;
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto) {

        memberService.findMember(answerPostDto.getMemberId());
        questionService.findQuestion(answerPostDto.getQuestionId());
        Answer answer = answerService.createAnswer(answerMapper.AnswerPostDtoToAnswer(answerPostDto));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody AnswerPatchDto answerPatchDto) {

        answerPatchDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(answerMapper.AnswerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(answerMapper.AnswerToAnswerResponseDto(answer)), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public  ResponseEntity<?> getAnswer(@PathVariable("answer-id") @Positive long answerId) {

        Answer answer = answerService.findVerifyAnswer(answerId);

        return new ResponseEntity<>(new SingleResponseDto<>(answerMapper.AnswerToAnswerResponseDto(answer)), HttpStatus.OK);

    }

    @GetMapping("/member/{member-id}") //질문 조회
    public ResponseEntity getQuestionByMemberId(@PathVariable("member-id") long memberId){
        List<AnswerResponseDto> response = answerService.findAnswerByMemberId(memberId);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAnswers() {
        List<AnswerResponseDto> answers = answerService.findAnswers();

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 한 사람이 만든 질문 하나의 답변들
    @GetMapping("/{member-id}/{question-id}") //답변 조회
    public ResponseEntity getQuestionByMemberId(@PathVariable("member-id") @Positive long memberId,
                                                @PathVariable("question-id") @Positive long questionId){

        List<AnswerResponseDto> response = answerService.findAnswersByMemberIdAndQuestionId(memberId, questionId);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // Vote 기능
    @PostMapping("/answerVoteUp/{answer-id}/{member-id}")
    public ResponseEntity<?> voteAnswerUp(@PathVariable("answer-id") long answerId,
                                          @PathVariable("member-id") long memberId) {

        Answer voteAnswerUp = answerService.answerVoteUp(answerId, memberId);
        AnswerResponseDto response = answerMapper.AnswerToAnswerResponseDto(voteAnswerUp);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/answerVoteDown/{answer-id}/{member-id}")
    public ResponseEntity<?> voteAnswerDown(@PathVariable("answer-id") long answerId,
                                            @PathVariable("member-id") long memberId) {
        memberService.findMember(memberId);
        Answer voteAnswerDown = answerService.answerVoteDown(answerId, memberId);
        AnswerResponseDto response = answerMapper.AnswerToAnswerResponseDto(voteAnswerDown);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
