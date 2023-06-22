package com.district11.stackoverflow.answerVote.repository;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import com.district11.stackoverflow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {

    Optional<AnswerVote> findByAnswer(Answer answer);

}
