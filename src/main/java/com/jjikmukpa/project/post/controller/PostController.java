package com.jjikmukpa.project.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
    @GetMapping("/post")
    public String post(){ return "layout/post/post"; }
}
