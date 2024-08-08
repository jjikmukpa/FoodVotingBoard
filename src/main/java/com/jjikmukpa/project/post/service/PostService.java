package com.jjikmukpa.project.post.service;

import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.post.model.dto.CreatePostDTO;
import com.jjikmukpa.project.post.model.dto.PostDTO;
import com.jjikmukpa.project.post.model.entity.Post;
import com.jjikmukpa.project.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public void createPost(CreatePostDTO postDTO, Member member){

        Post post = Post.builder()
                .postTitle(postDTO.getPostTitle())
                .content(postDTO.getContent())
                .createdDate(LocalDateTime.now())
                .member(member)
                .build();

        postRepository.save(post);
    }

    public Page<PostDTO> findAllPost(Pageable pageable) {

        pageable = PageRequest.of( // PageRequest.of -> Pageable 객체 조작
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("postNo").descending());

        Page<Post> postList = postRepository.findAll(pageable);

        return postList.map(post -> modelMapper.map(post, PostDTO.class));
    }

    public Post findPostById(int postNo) {

        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return post;
    }

    public void updatePost(int postNo, String postTitle, String content, LocalDateTime modifyDate){

        Post post = findPostById(postNo);

        post.setPostTitle(postTitle);
        post.setContent(content);
        post.setCreatedDate(modifyDate);

        postRepository.save(post);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public boolean isPostOwner(int postNo, String memberId) {
        Post post = findPostById(postNo);
        return post.getMember().getMemberId().equals(memberId);
    }

}
