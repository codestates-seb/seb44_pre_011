package com.district11.stackoverflow.tag.service;

import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.question.entity.QuestionTag;
import com.district11.stackoverflow.tag.entity.Tag;
import com.district11.stackoverflow.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Page<QuestionTag> findTag(int page, int size, long tagId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("tagId").descending());
        Tag tag = findVerifyTags(tagId);
        tag.setTagId(tagId);

        List<QuestionTag> questions = tag.getQuestionTagList()
                .stream()
                .filter(q->!q.getQuestion().getTags().contains(tagId))
                .collect(Collectors.toList());

        if(questions == null || questions.isEmpty()){
            return new PageImpl<>(new ArrayList<>(),pageable,0);
        }

        return new PageImpl<>(questions,pageable,questions.size());
    }
    public Page<Tag> findTags(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("tagId").descending());
        return tagRepository.findAll(pageable);
    }
    public Tag findVerifyTags(long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findTag = optionalTag.orElseThrow(()-> new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
        return findTag;
    }
}
