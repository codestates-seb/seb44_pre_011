package com.district11.stackoverflow.question.mapper;

import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.dto.QuestionDto;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.entity.QuestionTag;
import com.district11.stackoverflow.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper{

    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.displayName", target = "displayName")
    QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions);

    default Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setMember( postToMember( questionPostDto ) );
        question.setTitle( questionPostDto.getTitle() );
        question.setContent( questionPostDto.getContent() );

        List<QuestionTag> questionTags = questionPostDto.getTags()
                .stream().map(questionTagString ->{
                    QuestionTag questionTag = new QuestionTag();
                    Tag tag = new Tag();
                    tag.setName(questionTagString);
                    questionTag.setTag(tag);
                    questionTag.setQuestion(question);
                    return questionTag;
                }).collect(Collectors.toList());
        question.setTags( questionTags );

        return question;
    }

    default Member postToMember(QuestionDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( post.getMemberId() );

        return member;
    }



}
