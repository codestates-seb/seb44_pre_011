package com.district11.stackoverflow.question.entity;
import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionTag> tags = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
