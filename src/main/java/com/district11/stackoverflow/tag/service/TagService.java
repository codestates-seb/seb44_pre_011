package com.district11.stackoverflow.tag.service;

import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.tag.dto.TagDto;
import com.district11.stackoverflow.tag.entity.Tag;
import com.district11.stackoverflow.tag.mapper.TagMapper;
import com.district11.stackoverflow.tag.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDto.Response> findTags() {
        List<Tag> findtags = tagRepository.findAll();
        return tagMapper.TagsToTagResponseDtos(findtags);
    }

    public Tag findVerifyTag(long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
    }
}
