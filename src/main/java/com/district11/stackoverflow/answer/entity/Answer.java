package com.district11.stackoverflow.answer.entity;

import com.district11.stackoverflow.answerVote.entity.AnswerVote;
import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ANSWERS")
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(columnDefinition = "text")          // columnDefinition : DB 컬럼 정보를 직접적으로 지정할 때 사용
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_REGISTRATION;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    // 답변 Vote
    @Column(nullable = false)
    private long answerVoteCount;

    /*
    @OneToMany(mappedBy = "answer", cascade = {CascadeType.REMOVE})
    private List<AnswerVote> answerVotes = new ArrayList<>();
     */



    // 답변 상태
    public enum AnswerStatus {
        ANSWER_REGISTRATION("답변 등록"),
        ANSWER_DELETE("답변 삭제");

        @Getter
        private String status;

        AnswerStatus(String status) {
            this.status = status;
        }

    }

    /*
    public void addAnswerVote(AnswerVote answerVote) { //answer 와 answerVote 매핑
        this.answerVotes.add(answerVote);
        answerVote.setAnswer(this);
        updateScore();
    }

    public void removeAnswerVote(AnswerVote answerVote) {
        this.answerVotes.remove(answerVote);
        if(answerVote.getAnswer() != this) {
            answerVote.setAnswer(this);
        }
        updateScore();
    }

    public void updateScore() {
        int voteCount = 0;

        for (AnswerVote answerVote : answerVotes) {
            if(answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.VOTE_UP) {
                voteCount++;
            } else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.VOTE_DOWN) {
                voteCount--;
            }
        }

        this.voteCount = voteCount;
    }

     */


}
