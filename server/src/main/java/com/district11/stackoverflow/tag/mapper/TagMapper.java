package com.district11.stackoverflow.tag.mapper;

import com.district11.stackoverflow.tag.dto.TagDto;
import com.district11.stackoverflow.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag tagPostDtoToTag(TagDto.Post requestBody);

    Tag tagPatchDtoToTag(TagDto.Patch requestBody);

    TagDto.Response tagToTagResponseDto(Tag tag);

    List<TagDto.Response> TagsToTagResponseDtos(List<Tag> tags);
}
