package com.district11.stackoverflow.tag.mapper;

import com.district11.stackoverflow.tag.dto.TagDto;
import com.district11.stackoverflow.tag.entity.Tag;
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
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag tagPostDtoToTag(TagDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setName( requestBody.getName() );
        tag.setInfo( requestBody.getInfo() );

        return tag;
    }

    @Override
    public Tag tagPatchDtoToTag(TagDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Tag tag = new Tag();

        return tag;
    }

    @Override
    public TagDto.Response tagToTagResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto.Response response = new TagDto.Response();

        return response;
    }

    @Override
    public List<TagDto.Response> TagsToTagResponseDtos(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagDto.Response> list = new ArrayList<TagDto.Response>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( tagToTagResponseDto( tag ) );
        }

        return list;
    }
}
