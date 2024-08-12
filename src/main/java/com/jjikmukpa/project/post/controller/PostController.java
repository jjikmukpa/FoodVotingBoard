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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
    public String createPost() {
        return "layout/post/createPost";
    }

    @GetMapping("/postList")
    public String findAllPost(@PageableDefault Pageable pageable, String search, Model model,
                              @RequestParam(value = "searchType", required = false) String searchType) {
        Page<PostDTO> postList;

        // 검색어가 있는 경우 검색 게시글 조회
        if (search != null && !search.isEmpty()) {
            switch (searchType){
                case "postTitle":
                    postList = postService.searchTitle(search, pageable);
                    break;
                case "content":
                    postList = postService.searchContent(search, pageable);
                    break;
                case "both":
                    postList = postService.searchTitleAndContent(search, pageable);
                    break;
                default:
                    postList = postService.findAll(pageable);
                    break;
            }
        }else {
            postList = postService.findAll(pageable);
        }

        // 페이지네이션 정보
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(postList);

        // 모델에 데이터 추가
        model.addAttribute("paging", paging);
        model.addAttribute("postList", postList);
        model.addAttribute("searchType",searchType);
        model.addAttribute("searchTerm", search != null ? search : ""); // 검색어를 모델에 추가하여 템플릿에서 사용할 수 있도록 함

        return "layout/post/post"; // Thymeleaf 템플릿 경로
    }

    @GetMapping("/detailPost/{postNo}")
    public String detailPost(@PathVariable int postNo, @AuthenticationPrincipal UserDetails userDetails,
                             Model model) {

        Post post = postService.findPostById(postNo);
        String currentUserId = userDetails.getUsername();

        model.addAttribute("post", post);
        model.addAttribute("currentUserId", currentUserId);

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
            model.addAttribute("message", "권한이 없습니다.");
            return "layout/error/accessDenied";
        }

        boolean isDeleted = postService.deletePost(postNo);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
            return "redirect:/post/postList";
        } else {
            redirectAttributes.addFlashAttribute("message", "게시글 삭제에 실패했습니다.");
            return "redirect:/post/detailPost";
        }
    }

}
