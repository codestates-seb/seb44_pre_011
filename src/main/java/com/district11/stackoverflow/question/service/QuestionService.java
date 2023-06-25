package com.district11.stackoverflow.question.service;

import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.question.dto.QuestionResponseDto;
import com.district11.stackoverflow.question.entity.Question;
import com.district11.stackoverflow.question.entity.QuestionTag;
import com.district11.stackoverflow.question.mapper.QuestionMapper;
import com.district11.stackoverflow.question.repository.QuestionRepository;
import com.district11.stackoverflow.tag.entity.Tag;
import com.district11.stackoverflow.tag.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.tagRepository = tagRepository;
    }

    public Question createQuestion(Question question) {
        //todo: Tags 서비스 로직 추가해야함
        List<QuestionTag> questionTags = question.getTags()
                .stream().map(questionTag -> {
                    String tagName = questionTag.getTag().getName();
                    Optional<Tag> optionalTag = tagRepository.findByName(tagName);

                    if (optionalTag.isPresent()) questionTag.setTag(optionalTag.get());
                    else {
                        questionTag.getTag();
                        Tag newTag = tagRepository.save(questionTag.getTag());
                        questionTag.setTag(newTag);
                    }
                    return questionTag;
                }).collect(Collectors.toList());
        question.setTags(questionTags);

        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findQuestion(question.getQuestionId());
        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(findQuestion::setContent);

        return findQuestion;
    }

    public List<QuestionResponseDto> findQuestionsByMemberId(long memberId) {
        List<QuestionResponseDto> Dto = findQuestions();
        return Dto.stream().filter(d -> d.getMemberId() == memberId).collect(Collectors.toList());
    }

    public void deleteQuestion(long questionId) {
        Question question = findQuestion(questionId);
        questionRepository.deleteById(questionId);
    }

    public Question findQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }


    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size, Sort.by("questionId").descending()));
    }


    public List<QuestionResponseDto> findQuestions() {
        List<Question> questions = questionRepository.findAll(Sort.by(Sort.Direction.DESC, "questionId"));
        return questionMapper.questionToQuestionResponseDtos(questions);
    }

    // 검색 기능
    public List<QuestionResponseDto> questionSearchList(String searchKeyword){
        List<Question> questions = questionRepository.findByTitleContaining(searchKeyword);
        return questionMapper.questionToQuestionResponseDtos(questions);
    }

    // Vote 기능
    public Question questionVoteUp(long questionId, long memberId) {

        Question findQuestion = findQuestion(questionId);
        Map<Long, String> map = findQuestion.getQuestionMap();
        if (!map.containsKey(memberId) || map.get(memberId).equals("down") || map.get(memberId).equals("none")) {
            if (!map.containsKey(memberId) || map.get(memberId).equals("none")) map.put(memberId, "up");
            else if (map.get(memberId).equals("down")) map.put(memberId, "none");
            findQuestion.setQuestionVoteCount(findQuestion.getQuestionVoteCount() + 1);
            Question updateQuestion = questionRepository.save(findQuestion);
            return updateQuestion;
        } else throw new BusinessLogicException(ExceptionCode.VOTE_EXISTS);
    }


    public Question questionVoteDown(long questionId, long memberId) {
        Question findQuestion = findQuestion(questionId);
        Map<Long, String> map = findQuestion.getQuestionMap();
        if (!map.containsKey(memberId) || map.get(memberId).equals("up") || map.get(memberId).equals("none")) {
            if (!map.containsKey(memberId) || map.get(memberId).equals("none")) map.put(memberId, "down");
            else if (map.get(memberId).equals("up")) map.put(memberId, "none");
            findQuestion.setQuestionVoteCount(findQuestion.getQuestionVoteCount() - 1);
            Question updateQuestion = questionRepository.save(findQuestion);

            return updateQuestion;
        } else throw new BusinessLogicException(ExceptionCode.VOTE_EXISTS);
    }
}