package com.district11.stackoverflow.member.controller;


import com.district11.stackoverflow.dto.MultiResponseDto;
import com.district11.stackoverflow.dto.SingleResponseDto;
import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.mapper.MemberMapper;
import com.district11.stackoverflow.member.service.MemberService;
import com.district11.stackoverflow.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/members")
@Validated
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = memberService.createMember(memberMapper.memberPostDtotoMember(requestBody));
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }


//    @GetMapping
//    public ResponseEntity getMembers(@Positive @RequestParam int page,
//                                     @Positive @RequestParam int size) {
//        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
//        List<Member> members = pageMembers.getContent();
//        return new ResponseEntity(
//                new MultiResponseDto(memberMapper.membersToMemberResponseDtos(members), pageMembers), HttpStatus.OK
//        );
//    }
    @GetMapping
    public ResponseEntity getMembers() {
        List<MemberDto.Response> members = memberService.findMembers();


        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PatchMapping("/{member-Id}")
    public ResponseEntity patchMember(@PathVariable @Positive long memberId,
                                      @Valid @RequestBody MemberDto.Patch requestBody) {
        Member member = memberService.updateMember(memberMapper.memberPatchDtotoMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member))
                , HttpStatus.OK
        );
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member))
                , HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload/{member-id}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @PathVariable("member-id") @Positive long memberId) {
        if (file.isEmpty()) {
            // 파일이 없는 경우에 대한 처리
            return "파일을 선택해주세요.";
        }

        try {
            // 파일을 저장할 경로 설정
            String uploadDir = "/upload/img";
            Path filePath = Path.of(uploadDir, file.getOriginalFilename());

            // 파일을 지정된 경로로 복사
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Member member = memberService.findMember(memberId);
            member.setProfileImg(filePath.toString());
            // 파일 저장이 완료되었을 때 추가적인 처리를 수행할 수 있습니다.

            return "파일 업로드가 완료되었습니다.";
        } catch (IOException e) {
            // 파일 저장 중 에러가 발생한 경우에 대한 처리
            return "파일 업로드 중 에러가 발생했습니다.";
        }
    }
}


