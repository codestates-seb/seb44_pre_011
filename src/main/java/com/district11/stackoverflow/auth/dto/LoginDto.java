package com.district11.stackoverflow.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
public class LoginDto {
    private String username;
    private String password;
}