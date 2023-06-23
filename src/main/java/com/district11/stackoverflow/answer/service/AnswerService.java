package com.district11.stackoverflow.answer.service;

import com.district11.stackoverflow.answer.dto.AnswerResponseDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answer.mapper.AnswerMapper;
import com.district11.stackoverflow.answer.repository.AnswerRepository;
import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.service.MemberService;
import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository, MemberService memberService,
                         QuestionService questionService, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.questionService = questionService;
        this.answerMapper = answerMapper;
    }

    public Answer createAnswer(Answer answer) {

        // 답변을 할 수 있는지 검증
        verifyAnswer(answer);

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
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);      // Optional : Null값 허용

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }

    // createAnswer 하기위해서 필요
    public void verifyAnswer(Answer answer) {

        // member가 존재하는지 확인
        Member member = memberService.findMember(answer.getMember().getMemberId());
        answer.setMember(member);

        // 질문이 존재하는지 확인
        Question question = questionService.findQuestion(answer.getQuestion().getQuestionId());
        answer.setQuestion(question);
    }

    public List<AnswerResponseDto> findAnswersByMemberIdAndQuestionId(long memberId, long questionId) {
        List<AnswerResponseDto> Dto = findAnswers();
        return Dto.stream().filter(id -> id.getMemberId() == memberId && id.getQuestionId() == questionId).collect(Collectors.toList());
    }

    public List<AnswerResponseDto> findAnswers() {
        List<Answer> answers = answerRepository.findAll();
        return answerMapper.AnswerToAnswerResponseDtos(answers);
    }

    public List<AnswerResponseDto> findAnswerByMemberId(long memberId) {
        List<AnswerResponseDto> Dto = findAnswers();
        return Dto.stream().filter(d -> d.getMemberId() == memberId).collect(Collectors.toList());
    }

    // Vote 기능
    public Answer answerVoteUp(long answerId, long memberId) {
        Answer findAnswer = findVerifyAnswer(answerId);
        Map<Long, String> map = findAnswer.getAnswerMap();
        if (!map.containsKey(memberId) || map.get(memberId).equals("down") || map.get(memberId).equals("none")) {
            if (!map.containsKey(memberId) || map.get(memberId).equals("none")) map.put(memberId, "up");
            else if (map.get(memberId).equals("down")) map.put(memberId, "none");
            findAnswer.setAnswerVoteCount(findAnswer.getAnswerVoteCount() + 1);
            Answer updateAnswer = answerRepository.save(findAnswer);
            return updateAnswer;
        } else throw new BusinessLogicException(ExceptionCode.VOTE_EXISTS);
    }

    public Answer answerVoteDown(long answerId, long memberId) {
        Answer findAnswer = findVerifyAnswer(answerId);
        Map<Long, String> map = findAnswer.getAnswerMap();
        if (!map.containsKey(memberId) || map.get(memberId).equals("up") || map.get(memberId).equals("none")) {
            if (!map.containsKey(memberId) || map.get(memberId).equals("none")) map.put(memberId, "down");
            else if (map.get(memberId).equals("up")) map.put(memberId, "none");
            findAnswer.setAnswerVoteCount(findAnswer.getAnswerVoteCount() - 1);
            Answer updateAnswer = answerRepository.save(findAnswer);

            return updateAnswer;
        }else throw new BusinessLogicException(ExceptionCode.VOTE_EXISTS);
    }

}
