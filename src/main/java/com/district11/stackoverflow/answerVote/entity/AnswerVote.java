package com.district11.stackoverflow.answerVote.entity;

import com.district11.stackoverflow.answer.entity.Answer;
import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class AnswerVote extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerVoteId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerVoteStatus answerVoteStatus;

    // 답변 vote 상태
    public enum AnswerVoteStatus {
        VOTE_NONE("VOTE_NONE"),
        VOTE_UP("VOTE_UP"),
        VOTE_DOWN("VOTE_DOWN");


        @Getter
        private String status;

        AnswerVoteStatus(String status) {
            this.status = status;
        }
    }

}
