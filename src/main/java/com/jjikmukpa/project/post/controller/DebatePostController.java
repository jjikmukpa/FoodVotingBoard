package com.jjikmukpa.project.post.controller;

import com.jjikmukpa.project.common.Pagenation;
import com.jjikmukpa.project.common.PagingButtonInfo;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.service.MemberService;
import com.jjikmukpa.project.post.model.dto.CreateDebatePostDTO;
import com.jjikmukpa.project.post.model.dto.DebatePostDTO;
import com.jjikmukpa.project.post.model.entity.DebatePost;
import com.jjikmukpa.project.post.service.DebatePostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/debatePost")
@RequiredArgsConstructor
@Slf4j
public class DebatePostController {

    private final DebatePostService debatepostService;
    private final MemberService memberService;

    @PostMapping("/create")
    public String createPost(@AuthenticationPrincipal UserDetails userDetails, CreateDebatePostDTO createdebatepostDTO) {

        String memberId = userDetails.getUsername();

        Member member = memberService.findMemberById(memberId);
        log.info("member : {}", member.getName());

        debatepostService.createDebatePost(createdebatepostDTO,member);

        return "redirect:/";
    }

    @GetMapping("/createDebatePost")
    public String createDebatePost(){ return "layout/post/createDebatePost"; }

    @GetMapping("/debatePostList")
    public String findAllPost(@PageableDefault Pageable pageable, Model model){


        // 모든 게시글을 가져옵니다,.
        Page<DebatePostDTO> debatePostList = debatepostService.findAllPost(pageable);
        log.info(String.valueOf(debatePostList.getTotalElements()));

        //페이지네이션 정보
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(debatePostList);

        // 모델에 데이터 추가
        model.addAttribute("paging", paging);
        model.addAttribute("debatePostList", debatePostList);

        return "layout/post/debatePost"; //Thymeleaf 템플릿 경로
    }

    @GetMapping("/detailDebatePost/{debatePostNo}")
    public String detailPost(@PathVariable int debatePostNo, Model model){

        DebatePost debatePost = debatepostService.findPostById(debatePostNo);

        model.addAttribute("debatePost", debatePost);

        return "layout/post/detailDebatePost";
    }

}
