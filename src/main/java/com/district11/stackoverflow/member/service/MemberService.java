package com.district11.stackoverflow.member.service;

import com.district11.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.mapper.MemberMapper;
import com.district11.stackoverflow.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberMapper memberMapper;

    public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository, CustomAuthorityUtils authorityUtils, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
        this.memberMapper = memberMapper;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        // 추가: Password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // 추가: DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);


        return memberRepository.save(member);
    }


    public Member updateMember(Member member) {
        Member findMember = findMember(member.getMemberId());
        return memberRepository.save(member);
    }

    public Member findMember(long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    public List<MemberDto.Response> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto.Response> response =
                members.stream()
                        .map(member -> memberMapper.memberToMemberResponseDto(member))
                        .collect(Collectors.toList());
        return response;
    }

    public void deleteMember(long memberId) {
        Member findMember = findMember(memberId);
        memberRepository.deleteById(memberId);
    }


    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

}
