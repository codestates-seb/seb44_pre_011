package com.district11.stackoverflow.auth;

import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import com.district11.stackoverflow.member.dto.CustomMemberDto;
import com.district11.stackoverflow.member.dto.MemberDto;
import com.district11.stackoverflow.member.entity.Member;
import com.district11.stackoverflow.member.repository.MemberRepository;
import com.district11.stackoverflow.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@Slf4j
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberRepository memberRepository;

    public OAuth2MemberSuccessHandler(JwtTokenizer jwtTokenizer,
                                      CustomAuthorityUtils authorityUtils, MemberRepository memberRepository) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberRepository = memberRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email")); // OAuth2User 객체로부터 Resource Owner의 이메일 주소를 얻기
        String displayName = String.valueOf(oAuth2User.getAttributes().get("name")); // 이름을 얻기
//        String profileImage = String.valueOf(oAuth2User.getAttributes().get("picture")); // 프로필 이미지 URL을 얻기

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        List<String> authorities = authorityUtils.createRoles(email);           // 권한 정보 생성

        Member member;
        if (optionalMember.isEmpty()) { // 이메일이 저장되어 있지 않은 경우
            member = saveMember(email, displayName); // Resource Owner의 정보를 DB에 저장
        } else {
            member = optionalMember.get();
        }

        redirect(request, response, member, authorities);  // Access Token과 Refresh Token을 생성해서 Frontend 애플리케이션에 전달하기 위해 Redirect
    }

    private Member saveMember(String email, String displayName) {
        memberRepository.findByEmail(email).ifPresent(it ->
        {throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS, String.format("%s is duplicated 버그발생! OAuth2 핸들러 검사하시오.", email));
        });
        Member member = new Member();
        member.setEmail(email);
        member.setDisplayName(displayName);
//       member.setProfileImage(profileImage);
        Member savedMember = memberRepository.save(member);
        List<String> roles = authorityUtils.createRoles(email);
        savedMember.setRoles(roles);
        CustomMemberDto.from(savedMember);

        return member;
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, Member member, List<String> authorities) throws IOException {
        String accessToken = delegateAccessToken(member, authorities);  // JWT Access Token 생성
        String refreshToken = delegateRefreshToken(member.getEmail());  // Refresh Token 생성

        response.setHeader("Authorization", "WishJWT " + accessToken);  // 클리이언트한테 Access Token 보내주기 (이후에 클라이언트 측에서 백엔드 애플리케이션 측에 요청을 보낼 때마다 request header에 추가해서 클라이언트 측의 자격을 증명하는데 사용)
        response.setHeader("Refresh", refreshToken);                   // 클리이언트한테 Refresh Token 보내주기

        String uri = createURI(accessToken, refreshToken).toString();   // Access Token과 Refresh Token을 포함한 URL을 생성
        getRedirectStrategy().sendRedirect(request, response, uri);   // Frontend 애플리케이션 쪽으로 리다이렉트

        log.info("# Authenticated successfully!");

        // response 헤더 정보 로그 출력
        for (String headerName : response.getHeaderNames()) {
            log.info(headerName + ": " + response.getHeader(headerName));
        }
    }

    private String delegateAccessToken(Member member, List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberEmail", member.getEmail());
        claims.put("memberNickName", member.getDisplayName());
        claims.put("roles", authorities);

        String subject = member.getEmail();

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost") // Todo 리액트 서버 도메인 주소 입력
//                .port(3000) // Todo 포트번호 변경 주의
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
