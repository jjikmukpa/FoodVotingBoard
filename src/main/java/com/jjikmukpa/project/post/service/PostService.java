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


    public Post findPostById(long postNo) {

        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return post;
    }

    public void updatePost(long postNo, String postTitle, String content, LocalDateTime modifyDate){

        Post post = findPostById(postNo);

        post.setPostTitle(postTitle);
        post.setContent(content);
        post.setCreatedDate(modifyDate);

        postRepository.save(post);
    }

    public boolean isPostOwner(long postNo, String memberId) {
        Post post = findPostById(postNo);
        return post.getMember().getMemberId().equals(memberId);
    }

    public boolean deletePost(long postNo) {
        // 실제 데이터베이스에서 게시글을 삭제하는 로직을 구현
        try {
            if (postRepository.existsById(postNo)) {
                postRepository.deleteById(postNo);
                return true; // 삭제 성공
            }else {
                return false; // 게시글이 존재하지 않음
            }
        } catch (Exception e) {
            // 예외가 발생하면 false 반환
            return false;
        }

    }

    public Page<PostDTO> findAll(Pageable pageable) {

        // 페이지 번호 조정: 1페이지부터 시작하도록 설정
        pageable = PageRequest.of(
                Math.max(pageable.getPageNumber() - 1, 0),
                10, //페이지 크기 고정
                Sort.by("postNo").descending()
        );
        // 페이징된 DebatePost 목록 조회
        Page<Post> PostList = postRepository.findAll(pageable);

        // Post를 PostDTO로 변환하여 반환
        return PostList.map(post -> modelMapper.map(post, PostDTO.class));
    }

    // 제목+내용 검색하는 기능
    public Page<PostDTO> searchTitleAndContent(String searchTerm, Pageable pageable) {
        Page<Post> PostList = postRepository.findByPostTitleContainingOrContentContaining(searchTerm, searchTerm, pageable);
        return PostList.map(Post -> modelMapper.map(Post, PostDTO.class));
    }

    public Page<PostDTO> searchTitle(String searchTerm, Pageable pageable) {
        Page<Post> PostList = postRepository.findByPostTitleContainingIgnoreCase(searchTerm, pageable);
        return PostList.map(Post -> modelMapper.map(Post, PostDTO.class));
    }

    public Page<PostDTO> searchContent(String searchTerm, Pageable pageable) {
        Page<Post> PostList = postRepository.findByContentContainingIgnoreCase(searchTerm, pageable);
        return PostList.map(Post -> modelMapper.map(Post, PostDTO.class));
    }
}
