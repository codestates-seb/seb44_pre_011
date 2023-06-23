package com.district11.stackoverflow.auth.handler;

import com.district11.stackoverflow.exception.BusinessLogicException;
import com.district11.stackoverflow.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 인증 실패 시, 에러 로그를 기록하거나 error response를 전송할 수 있다.
        log.error("# Authentication failed: {}", exception.getMessage());

        BusinessLogicException businessLogicException = new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 상태 코드 설정
        response.getWriter().write(businessLogicException.getMessage()); // 응답 내용 작성
        response.getWriter().flush();

    }

}

