package com.district11.stackoverflow.question.repository;

import com.district11.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTitleContaining(String searchKeyword);

}
