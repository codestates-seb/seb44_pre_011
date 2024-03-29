package com.district11.stackoverflow.member.entity;

import com.district11.stackoverflow.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MEMBERS")
@Getter
@Setter
@NoArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Email
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 100, nullable = false, unique = true)
    private String displayName;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    private String profileImg;


    public Member(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    // OAuth 추가
    public Member(String email) {
        this.email = email;
    }


}
