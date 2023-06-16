package com.district11.stackoverflow.sliceTest;

import com.district11.stackoverflow.question.repository.QuestionRepository;
import com.district11.stackoverflow.question.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class QuestionServiceTest {

    // Test 주체
    QuestionService questionService;

    // Test 협력자
    @MockBean
    QuestionRepository questionRepository;


    // Test를 실행하기 전마다 MemberService에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setUp(){
        questionService = new QuestionService(questionRepository);
    }
}