package com.district11.stackoverflow.question.repository;

import com.district11.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {

}
