package com.district11.stackoverflow.member.controller;


import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.mapper.MemberMapper;
import com.district11.stackoverflow.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

//    @PostMapping
//    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
//        Member member = memberMapper.memberPostDtotoMember(requestBody);
//        return new ResponseEntity<>();
//    }
//
//    @PatchMapping("/{member-Id}")
//    public ResponseEntity patchMember(){
//        return new ResponseEntity<>();
//    }
//
//    @GetMapping
//    public ResponseEntity getMembers() {
//        return new ResponseEntity<>();
//    }
//
//    @GetMapping("/{member-id}")
//    public ResponseBody getMember() {
//        return new ResponseEntity<>();
//    }
//
//    @DeleteMapping("/{member-id}")
//    public ResponseBody deleteMember() {
//        return new ResponseEntity<>();
//    }
}
