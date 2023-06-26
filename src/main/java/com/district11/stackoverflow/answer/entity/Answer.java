package com.district11.stackoverflow.answer.entity;

import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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

    @ElementCollection
    @CollectionTable(name = "answer_map", joinColumns = @JoinColumn(name = "answer_id"))
    @MapKeyColumn(name = "map_key")
    @Column(name = "map_value")
    public Map<Long, String> AnswerMap = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "question_map", joinColumns = @JoinColumn(name = "question_id"))
    @MapKeyColumn(name = "map_key")
    @Column(name = "map_value")
    public Map<Long, String> questionMap = new HashMap<>();

    // 답변 상태
    public enum AnswerStatus {
        ANSWER_REGISTRATION("답변 등록"),
        ANSWER_DELETE("답변 삭제");

        @Getter
        private final String status;

        AnswerStatus(String status) {
            this.status = status;
        }

    }

}
