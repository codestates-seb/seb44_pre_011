package com.district11.stackoverflow.member.dto;

import com.district11.stackoverflow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CustomMemberDto {

    private Long memberId;
    private String email;
    private String password;
    private String displayName;

    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public static void from(Member entity) {
        new CustomMemberDto(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getDisplayName(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    // from : factory method 파라미터로 넘어온 값들을 해당 클래스의 인스턴스로 변환할 때 사용한다.
}
