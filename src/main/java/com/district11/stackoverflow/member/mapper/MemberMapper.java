package com.district11.stackoverflow.member.mapper;

import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtotoMember(MemberDto.Post requestBody);
    Member memberPatchDtotoMember(MemberDto.Patch requestBody);
}
