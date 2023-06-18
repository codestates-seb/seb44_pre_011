package com.district11.stackoverflow.answer.repository;

import com.district11.stackoverflow.answer.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {

}
