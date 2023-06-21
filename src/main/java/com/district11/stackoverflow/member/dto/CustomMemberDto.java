package com.district11.stackoverflow.member.dto;

import com.district11.stackoverflow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CustomMemberDto {

    private Long memberId;
    private String displayName;
    private String email;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public static CustomMemberDto from(Member entity) {
        return new CustomMemberDto(
                entity.getMemberId(),
                entity.getDisplayName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
