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
import org.springframework.web.multipart.MultipartFile;

import com.jjikmukpa.project.post.model.Option;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/debatePost")
@RequiredArgsConstructor
@Slf4j
public class DebatePostController {

    private final DebatePostService debatepostService;
    private final MemberService memberService;

    @PostMapping("/create")
    public String createPost(@AuthenticationPrincipal UserDetails userDetails, CreateDebatePostDTO createdebatepostDTO,
                             @RequestParam("debatePostTitle") String title,
                             @RequestParam("content") String content,
                             @RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2) {

        String memberId = userDetails.getUsername();
        Member member = memberService.findMemberById(memberId);
        log.info("member : {}", member.getName());

        // DebatePost 객체 생성
        DebatePost debatePost = DebatePost.builder()
                .postTitle(title)
                .content(content)
                .createdDate(LocalDateTime.now()) // 생성 일자 설정
                .member(member)
                .build();

        // 파일 저장 로직
        String image1Path = debatepostService.saveFile(file1);
        String image2Path = debatepostService.saveFile(file2);

        // 이미지 경로 설정
        Option option = new Option(image1Path, image2Path); // Option 객체 생성
        debatePost.addOption(option); // DebatePost에 옵션 추가

        // 데이터베이스에 저장
        debatepostService.saveDebatePost(debatePost);

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

        model.addAttribute("viewCount", debatePost.getPostCount()); // 조회수 추가
        model.addAttribute("debatePost", debatePost);
        return "layout/post/debatePost/detailDebatePost";
    }


}