package com.district11.stackoverflow.answer.entity;

import com.district11.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(columnDefinition = "text")          // columnDefinition : DB 컬럼 정보를 직접적으로 지정할 때 사용
    private String content;

    // Question

    // Member
}
