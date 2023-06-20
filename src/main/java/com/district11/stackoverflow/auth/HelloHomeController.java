package com.district11.stackoverflow.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class HelloHomeController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public HelloHomeController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        var authorizedClient = authorizedClientService.loadAuthorizedClient("google", authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value: " + accessToken.getTokenValue());
        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());
        System.out.println("Access Token Scopes: " + accessToken.getScopes());
        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());
        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());

        return "hello-oauth2";
    }
}