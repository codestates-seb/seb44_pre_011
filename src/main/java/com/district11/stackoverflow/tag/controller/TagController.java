package com.district11.stackoverflow.tag.controller;

import com.district11.stackoverflow.tag.dto.TagDto;
import com.district11.stackoverflow.tag.entity.Tag;
import com.district11.stackoverflow.tag.mapper.TagMapper;
import com.district11.stackoverflow.tag.service.TagService;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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

    //태그 전체 조회
    @GetMapping
    public ResponseEntity getTags() {
        List<TagDto.Response> tagList = tagService.findTags();
        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    @GetMapping("/{tag-id}")
    public ResponseEntity getTag(@PathVariable("tag-id") @Positive long tagId) {
        Tag tag = tagService.findVerifyTag(tagId);
        TagDto.Response response = tagMapper.tagToTagResponseDto(tag);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
