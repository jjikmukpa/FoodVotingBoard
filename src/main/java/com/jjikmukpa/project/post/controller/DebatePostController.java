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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/debatePost")
@RequiredArgsConstructor
@Slf4j
public class DebatePostController {

    private final DebatePostService debatepostService;
    private final MemberService memberService;

    @PostMapping("/create")
    public String createPost(@AuthenticationPrincipal UserDetails userDetails, CreateDebatePostDTO createdebatepostDTO
                            ) {



        String memberId = userDetails.getUsername();
        Member member = memberService.findMemberById(memberId);
        log.info("member : {}", member.getName());

        debatepostService.createDebatePost(createdebatepostDTO,member);

        return "redirect:/debatePost/debatePostList";
    }

    @GetMapping("/createDebatePost")
    public String createDebatePost(){

        return "layout/post/debatePost/createDebatePost"; }

    @GetMapping("/debatePostList")
    public String findAllPost(@PageableDefault Pageable pageable, String search, Model model) {
        Page<DebatePostDTO> debatePostList;

        // 검색어가 있는 경우 검색 게시글 조회
        if (search != null && !search.isEmpty()) {
            debatePostList = debatepostService.searchDebatePosts(search,pageable); // 검색 기능 추가
        } else {
            debatePostList = debatepostService.findAll(pageable);
        }

        // 페이지네이션 정보
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(debatePostList);

        // 모델에 데이터 추가
        model.addAttribute("paging", paging);
        model.addAttribute("debatePostList", debatePostList);
        model.addAttribute("searchTerm", search); // 검색어를 모델에 추가하여 템플릿에서 사용할 수 있도록 함

        return "layout/post/debatePost/debatePost"; // Thymeleaf 템플릿 경로
    }

    @GetMapping("/detailDebatePost/{debatePostNo}")
    public String detailPost(@PathVariable int debatePostNo, Model model){
        debatepostService.increasePostCount(debatePostNo); // 조회수 증가

        DebatePost debatePost = debatepostService.findPostById(debatePostNo);

        model.addAttribute("postCount", debatePost.getPostCount()); // 조회수 추가
        model.addAttribute("debatePost", debatePost);
        return "layout/post/debatePost/detailDebatePost";
    }

}