package com.district11.stackoverflow.question.entity;

import com.district11.stackoverflow.audit.Auditable;
import com.district11.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public String formatCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return createdAt.format(formatter);
    }

    public String formatModifiedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return modifiedAt.format(formatter);
    }

    @ElementCollection
    @CollectionTable(name = "question_map", joinColumns = @JoinColumn(name = "question_id"))
    @MapKeyColumn(name = "map_key")
    @Column(name = "map_value")
    public Map<Long,String> map = new HashMap<>();

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionTag> tags = new ArrayList<>();

    // Vote 추가
    @Column(nullable = false)
    private long questionVoteCount;


    public void setMember(Member member) {
        this.member = member;
    }

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
    }
}