package com.district11.stackoverflow.answer.repository;

import com.district11.stackoverflow.answer.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    
    // Vote의 결과가 하나씩 올라갈 수 있게 만들기

}
