package com.district11.stackoverflow.question.mapper;

import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.dto.QuestionDto;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-26T18:55:51+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostDtoToQuestion(QuestionDto.Post qustionPostDto) {
        if ( qustionPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setMember( postToMember( qustionPostDto ) );
        question.setTitle( qustionPostDto.getTitle() );
        question.setContent( qustionPostDto.getContent() );

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatchDto.getQuestionId() );
        question.setTitle( questionPatchDto.getTitle() );
        question.setContent( questionPatchDto.getContent() );

        return question;
    }

    @Override
    public QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        long memberId = 0L;
        String displayName = null;
        long questionId = 0L;
        String title = null;
        String content = null;
        long questionVoteCount = 0L;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        Long memberId1 = questionMemberMemberId( question );
        if ( memberId1 != null ) {
            memberId = memberId1;
        }
        displayName = questionMemberDisplayName( question );
        if ( question.getQuestionId() != null ) {
            questionId = question.getQuestionId();
        }
        title = question.getTitle();
        content = question.getContent();
        questionVoteCount = question.getQuestionVoteCount();
        createdAt = question.getCreatedAt();
        modifiedAt = question.getModifiedAt();

        QuestionResponseDto questionResponseDto = new QuestionResponseDto( memberId, questionId, title, content, displayName, questionVoteCount, createdAt, modifiedAt );

        return questionResponseDto;
    }

    @Override
    public List<QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionResponseDto> list = new ArrayList<QuestionResponseDto>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }

    protected Member postToMember(QuestionDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( post.getMemberId() );

        return member;
    }

    private Long questionMemberMemberId(Question question) {
        if ( question == null ) {
            return null;
        }
        Member member = question.getMember();
        if ( member == null ) {
            return null;
        }
        Long memberId = member.getMemberId();
        if ( memberId == null ) {
            return null;
        }
        return memberId;
    }

    private String questionMemberDisplayName(Question question) {
        if ( question == null ) {
            return null;
        }
        Member member = question.getMember();
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
