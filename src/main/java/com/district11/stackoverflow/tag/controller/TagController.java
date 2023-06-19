package com.district11.stackoverflow.tag.controller;

import com.district11.stackoverflow.dto.MultiResponseDto;
import com.district11.stackoverflow.question.entity.QuestionTag;
import com.district11.stackoverflow.tag.dto.CustomResponseDto;
import com.district11.stackoverflow.tag.dto.OneTagResponseDto;
import com.district11.stackoverflow.tag.entity.Tag;
import com.district11.stackoverflow.tag.mapper.TagMapper;
import com.district11.stackoverflow.tag.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
@Validated
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    //태그 누르면 태그 관련 질문글 조회
    @GetMapping("/{tag-id}")
    public ResponseEntity getTag(@Positive @RequestParam("page") int page,
                                 @Positive @RequestParam("size") int size,
                                 @Positive @PathVariable("tag-id") long tagId) {

        Page<QuestionTag> questionPage = tagService.findTag(page - 1, size, tagId);
        List<QuestionTag> questionList = questionPage.getContent();

        List<OneTagResponseDto> tagResponseDtoList = tagMapper.tagToTagResponseDto(questionList);

        Tag tag = tagService.findVerifyTags(tagId);
        String name = tag.getName();

        CustomResponseDto responseDto = new CustomResponseDto(name,tagResponseDtoList,questionPage);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    //태그 전체 조회
    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam("page") int page,
                                  @Positive @RequestParam("size") int size){
        Page<Tag> tagPage = tagService.findTags(page-1,size);
        List<Tag> tagList = tagPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(tagMapper.tagToTagResponseDtos(tagList),tagPage),HttpStatus.OK);
    }
}
