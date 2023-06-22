package com.district11.stackoverflow.tag.mapper;

import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.entity.QuestionTag;
import com.district11.stackoverflow.tag.dto.AllTagResponseDto;
import com.district11.stackoverflow.tag.dto.OneTagResponseDto;
import com.district11.stackoverflow.tag.dto.TagDto;
import com.district11.stackoverflow.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    //[태그조회 : mapper]
    default List<OneTagResponseDto> tagToTagResponseDto(List<QuestionTag> questionList) {
        if (questionList == null) {
            return null;
        }
        List<OneTagResponseDto> responseDtoList = new ArrayList<OneTagResponseDto>(questionList.size());
        for (QuestionTag questionTag : questionList) {
            responseDtoList.add(questionTagToOneTagResponseDto(questionTag));
        }
        return responseDtoList;
    }


    default OneTagResponseDto questionTagToOneTagResponseDto(QuestionTag questionTag) {
        if (questionTag == null) {
            return null;
        }
        OneTagResponseDto responseDto = new OneTagResponseDto();
        responseDto.setQuestionId(questionTag.getQuestion().getQuestionId());
        responseDto.setTitle(questionTag.getQuestion().getTitle());
        responseDto.setContent(questionTag.getQuestion().getContent());
        responseDto.setCreationDate(questionTag.getQuestion().getCreatedAt());
        responseDto.setMemberId(questionTag.getQuestion().getMember().getMemberId());
        responseDto.setDisplayName(questionTag.getQuestion().getMember().getDisplayName());
        responseDto.setTags(changeTags(questionTag.getQuestion().getTags()));
        return responseDto;
    }


    //[태그조회 : member]
    default TagDto.TagUserResponseDto changeUser(Member member) {
        if (member == null) {
            return null;
        }
        TagDto.TagUserResponseDto responseDto = new TagDto.TagUserResponseDto();
        responseDto.setMemberId(member.getMemberId());
        responseDto.setDisplayName(member.getDisplayName());
        return responseDto;
    }

    default List<TagDto.TagQuestionResponseDto> changeTags(List<QuestionTag> list) {
        if (list == null) {
            return null;
        }
        List<TagDto.TagQuestionResponseDto> responseDtoList = new ArrayList<TagDto.TagQuestionResponseDto>(list.size());
        for (QuestionTag questionTag : list) {
            responseDtoList.add(questionTagToTagQuestionResponseDto(questionTag));
        }
        return responseDtoList;
    }


    default TagDto.TagQuestionResponseDto questionTagToTagQuestionResponseDto(QuestionTag questionTag) {
        if (questionTag == null) {
            return null;
        }
        TagDto.TagQuestionResponseDto responseDto = new TagDto.TagQuestionResponseDto();
        responseDto.setTagId(questionTag.getTag().getTagId());
        responseDto.setName(questionTag.getTag().getName());
        return responseDto;
    }

    default List<AllTagResponseDto> tagToTagResponseDtos(List<Tag> tagList) {
        if (tagList == null) {
            return null;
        }
        List<AllTagResponseDto> responseDtoList = new ArrayList<AllTagResponseDto>(tagList.size());
        for (Tag tag : tagList) {
            responseDtoList.add(SubResponseDtos(tag));
        }
        return responseDtoList;
    }

    default AllTagResponseDto SubResponseDtos(Tag tag) {
        if (tag == null) {
            return null;
        }
        AllTagResponseDto responseDto = new AllTagResponseDto();
        responseDto.setTagId(tag.getTagId());
        responseDto.setName(tag.getName());
        //responseDto.setQuestionAmount(tag.getQuestionTagList().size());
        return responseDto;
    }
}
