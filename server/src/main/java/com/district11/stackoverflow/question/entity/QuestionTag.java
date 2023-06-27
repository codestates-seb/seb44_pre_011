package com.district11.stackoverflow.question.entity;

import com.district11.stackoverflow.tag.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionTagId;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

}
