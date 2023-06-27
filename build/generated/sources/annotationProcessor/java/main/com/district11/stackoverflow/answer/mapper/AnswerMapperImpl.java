package com.district11.stackoverflow.answer.mapper;

import com.district11.stackoverflow.answer.dto.AnswerPatchDto;
import com.district11.stackoverflow.answer.dto.AnswerPostDto;
import com.district11.stackoverflow.answer.dto.AnswerResponseDto;
import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.entity.Question;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T10:21:17+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer AnswerPostDtoToAnswer(AnswerPostDto answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setMember( answerPostDtoToMember( answerPostDto ) );
        answer.setQuestion( answerPostDtoToQuestion( answerPostDto ) );
        answer.setContent( answerPostDto.getContent() );

        return answer;
    }

    @Override
    public Answer AnswerPatchDtoToAnswer(AnswerPatchDto answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( answerPatchDto.getAnswerId() );
        answer.setContent( answerPatchDto.getContent() );
        answer.setAnswerStatus( answerPatchDto.getAnswerStatus() );

        return answer;
    }

    @Override
    public AnswerResponseDto AnswerToAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        long memberId = 0L;
        long questionId = 0L;
        String displayName = null;
        long answerId = 0L;
        long answerVoteCount = 0L;
        String content = null;
        Answer.AnswerStatus answerStatus = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        Long memberId1 = answerMemberMemberId( answer );
        if ( memberId1 != null ) {
            memberId = memberId1;
        }
        Long questionId1 = answerQuestionQuestionId( answer );
        if ( questionId1 != null ) {
            questionId = questionId1;
        }
        displayName = answerMemberDisplayName( answer );
        if ( answer.getAnswerId() != null ) {
            answerId = answer.getAnswerId();
        }
        answerVoteCount = answer.getAnswerVoteCount();
        content = answer.getContent();
        answerStatus = answer.getAnswerStatus();
        createdAt = answer.getCreatedAt();
        modifiedAt = answer.getModifiedAt();

        AnswerResponseDto answerResponseDto = new AnswerResponseDto( memberId, questionId, answerId, displayName, answerVoteCount, content, answerStatus, createdAt, modifiedAt );

        return answerResponseDto;
    }

    @Override
    public List<AnswerResponseDto> AnswerToAnswerResponseDtos(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerResponseDto> list = new ArrayList<AnswerResponseDto>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( AnswerToAnswerResponseDto( answer ) );
        }

        return list;
    }

    protected Member answerPostDtoToMember(AnswerPostDto answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( answerPostDto.getMemberId() );
        member.setDisplayName( answerPostDto.getDisplayName() );

        return member;
    }

    protected Question answerPostDtoToQuestion(AnswerPostDto answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( answerPostDto.getQuestionId() );

        return question;
    }

    private Long answerMemberMemberId(Answer answer) {
        if ( answer == null ) {
            return null;
        }
        Member member = answer.getMember();
        if ( member == null ) {
            return null;
        }
        Long memberId = member.getMemberId();
        if ( memberId == null ) {
            return null;
        }
        return memberId;
    }

    private Long answerQuestionQuestionId(Answer answer) {
        if ( answer == null ) {
            return null;
        }
        Question question = answer.getQuestion();
        if ( question == null ) {
            return null;
        }
        Long questionId = question.getQuestionId();
        if ( questionId == null ) {
            return null;
        }
        return questionId;
    }

    private String answerMemberDisplayName(Answer answer) {
        if ( answer == null ) {
            return null;
        }
        Member member = answer.getMember();
        if ( member == null ) {
            return null;
        }
        String displayName = member.getDisplayName();
        if ( displayName == null ) {
            return null;
        }
        return displayName;
    }
}
