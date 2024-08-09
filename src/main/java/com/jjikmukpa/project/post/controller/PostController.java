package com.jjikmukpa.project.post.controller;

import com.jjikmukpa.project.common.Pagenation;
import com.jjikmukpa.project.common.PagingButtonInfo;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.service.MemberService;
import com.jjikmukpa.project.post.model.dto.CreatePostDTO;
import com.jjikmukpa.project.post.model.dto.PostDTO;
import com.jjikmukpa.project.post.model.entity.Post;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

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

        postService.createPost(createpostDTO, member);

        return "redirect:/post/postList";
    }

    @GetMapping("/createPost")
    public String createPost(int postNo,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model) {

        String currentUserId = userDetails.getUsername();

        if (!postService.isPostOwner(postNo, currentUserId)) {
            model.addAttribute("message", "이 페이지에 접근할 권한이 없습니다.");
            return "layout/error/accessDenied"; // 권한 없음 페이지로 리다이렉트
        }

        model.addAttribute("postNo", postNo);
        return "redirect:/post/postList"; // 게시글 상세 페이지로 이동
    }

    @GetMapping("/postList")
    public String findAllPost(@PageableDefault Pageable pageable, Model model) {

        Page<PostDTO> postList = postService.findAllPost(pageable);
        log.info(String.valueOf(postList.getTotalElements()));
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(postList);

        model.addAttribute("paging", paging);
        model.addAttribute("postList", postList);

        return "layout/post/post";
    }

    @GetMapping("/detailPost/{postNo}")
    public String detailPost(@PathVariable int postNo, Model model) {

        Post post = postService.findPostById(postNo);

        model.addAttribute("post", post);

        return "layout/post/detailPost";
    }

    @GetMapping("/modifyPost/{postNo}")
        public String modifyPost(@PathVariable("postNo") int postNo, @AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {

        String currentUserId = userDetails.getUsername();

        if (!postService.isPostOwner(postNo, currentUserId)) {
            model.addAttribute("message", "이 페이지에 접근할 권한이 없습니다.");
            return "layout/error/accessDenied";
        }

        Post post = postService.findPostById(postNo);
        model.addAttribute("post", post);

        return "layout/post/modifyPost";
    }

    @PostMapping("updatePost")
    public String updatePost(@RequestParam("postNo") int postNo,
                             @RequestParam("postTitle") String postTitle,
                             @RequestParam("content") String content) {


        LocalDateTime modifyDate = LocalDateTime.now();

        postService.updatePost(postNo, postTitle, content, modifyDate);

        return "redirect:/post/detailPost/" + postNo;
    }

    @PostMapping("/delete/{postNo}")
    public String deletePost(@PathVariable("postNo") int postNo,
                             @AuthenticationPrincipal UserDetails userDetails,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        String currentUserId = userDetails.getUsername();

        if (!postService.isPostOwner(postNo, currentUserId)) {
            model.addAttribute("message", "이 페이지에 접근할 권한이 없습니다.");
            return "layout/error/accessDenied";
        }

        boolean isDeleted = postService.deletePost(postNo);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
            return "redirect:/post/postList";
        }else {
            redirectAttributes.addFlashAttribute("message", "게시글 삭제에 실패했습니다.");
            return "redirect:/post/detailPost";
        }
    }

    @GetMapping("/confirmDelete/{postNo}")
    public String confirmDelete(@PathVariable("postNo") int postNo,
                               @AuthenticationPrincipal UserDetails userDetails,
                               Model model) {

        String currentUserId = userDetails.getUsername();

        if (!postService.isPostOwner(postNo, currentUserId)) {
            model.addAttribute("message", "이 페이지에 접근할 권한이 없습니다.");
            return "layout/error/accessDenied"; // 권한 없음 페이지로 리다이렉트
        }

        model.addAttribute("postNo", postNo);
        return "redirect:/post/detailPost/"+ postNo; // 게시글 상세 페이지로 이동
    }
}
