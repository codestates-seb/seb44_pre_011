package com.district11.stackoverflow.question.entity;
import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    /*
    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;
    */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
