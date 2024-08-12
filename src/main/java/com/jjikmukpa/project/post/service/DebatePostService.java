package com.jjikmukpa.project.post.service;

import com.jjikmukpa.project.exception.ResourceNotFoundException;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.post.model.dto.CreateDebatePostDTO;
import com.jjikmukpa.project.post.model.dto.DebatePostDTO;
import com.jjikmukpa.project.post.model.entity.DebatePost;
import com.jjikmukpa.project.post.repository.DebatePostRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class DebatePostService {

    private final DebatePostRepository debatepostRepository;
    private final ModelMapper modelMapper;
    private final String UPLOAD_DIR = "src/main/resource/static/uploads";

    public void createDebatePost(CreateDebatePostDTO debatePostDTO, Member member){

        DebatePost debatepost = DebatePost.builder()
                .postTitle(debatePostDTO.getDebatePostTitle())
                .content(debatePostDTO.getContent())
                .createdDate(LocalDateTime.now())
                .member(member)
                .build();

        debatepostRepository.save(debatepost);
    }

    public Page<DebatePostDTO> findAll(Pageable pageable) {

        // 페이지 번호 조정: 1페이지부터 시작하도록 설정
        pageable = PageRequest.of(
                Math.max(pageable.getPageNumber() - 1, 0),
                10, //페이지 크기 고정
                Sort.by("postNo").descending()
        );
        // 페이징된 DebatePost 목록 조회
        Page<DebatePost> debatePostList = debatepostRepository.findAll(pageable);

        // DebatePost를 DebatePostDTO로 변환하여 반환
        return debatePostList.map(debatepost -> modelMapper.map(debatepost, DebatePostDTO.class));
    }

    public DebatePost findPostById(int debatePostNo) {

        DebatePost debatePost = debatepostRepository.findById(debatePostNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return debatePost;
    }

    public String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        // 파일 이름 생성
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destinationFile = new File(UPLOAD_DIR + fileName);

        try {
            // 파일을 지정된 경로에 저장
            file.transferTo(destinationFile);
            return fileName; // 저장된 파일 이름 반환
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void saveDebatePost(DebatePost debatePost) {
        // 데이터베이스에 debatePost 저장하는 로직을 구현
        // 예: debatePostRepository.save(debatePost);
    }

    // 검색 하는 기능
    public Page<DebatePostDTO> searchDebatePosts(String searchTerm, Pageable pageable) {

        // 페이지 번호 조정: 1페이지부터 시작하도록 설정
        pageable = PageRequest.of(
                Math.max(pageable.getPageNumber() - 1, 0),
                10, //페이지 크기 고정
                Sort.by("postNo").descending()
        );
        // 페이징된 Search 목록 조회
        Page<DebatePost> debatePostList = debatepostRepository.findByPostTitleContainingOrContentContaining(searchTerm, searchTerm, pageable);

        return debatePostList.map(debatePost -> modelMapper.map(debatePost, DebatePostDTO.class));
    }

    @Transactional
    public void increasePostCount(int debatePostNo) {
        DebatePost debatePost = debatepostRepository.findById(debatePostNo)
                .orElseThrow(() -> new ResourceNotFoundException("게시물을 찾을 수 없습니다."));
        debatePost.incrementPostCount();
        debatepostRepository.save(debatePost);
    }
}