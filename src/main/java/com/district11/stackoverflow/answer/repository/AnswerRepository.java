package com.district11.stackoverflow.answer.repository;

import com.district11.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
