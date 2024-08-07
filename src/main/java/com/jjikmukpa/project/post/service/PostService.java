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

}
