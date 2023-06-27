package com.district11.stackoverflow.tag.entity;

import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.question.entity.QuestionTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String info;

    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionTags = new ArrayList<>();

    public void setQuestionTags(QuestionTag questionTags) {
        this.questionTags.add(questionTags);
    }

}
