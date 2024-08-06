package com.jjikmukpa.project.post.controller;

import com.jjikmukpa.project.common.Pagenation;
import com.jjikmukpa.project.common.PagingButtonInfo;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.service.MemberService;
import com.jjikmukpa.project.post.model.dto.CreatePostDTO;
import com.jjikmukpa.project.post.model.dto.PostDTO;
import com.jjikmukpa.project.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;


    @PostMapping("/create")
    public String createPost(@AuthenticationPrincipal UserDetails userDetails, CreatePostDTO createpostDTO) {

        String memberId = userDetails.getUsername();

        Member member = memberService.findMemberById(memberId);
        log.info("member : {}", member.getName());

        postService.createPost(createpostDTO,member);

        return "redirect:/";
    }

    @GetMapping("/createPost")
    public String createPost(){ return "layout/post/createPost"; }

    @GetMapping("/postList")
    public String findAllPost(@PageableDefault Pageable pageable, Model model){

        Page<PostDTO> postList = postService.findAllPost(pageable);
        log.info(String.valueOf(postList.getTotalElements()));
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(postList);

        model.addAttribute("paging", paging);
        model.addAttribute("postList", postList);

        return "layout/post/post";
    }

}
