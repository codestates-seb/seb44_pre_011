package com.district11.stackoverflow.question.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController{
    @GetMapping
    public String index(){
        return "Hello World!";
    }
}