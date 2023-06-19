package com.district11.stackoverflow.tag.entity;

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
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;
    @Column(nullable = false)
    private String name;


    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionTagList = new ArrayList<>();

}
