package com.jjikmukpa.project.post.service;

import com.jjikmukpa.project.exception.ResourceNotFoundException;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.post.model.dto.CreateDebatePostDTO;
import com.jjikmukpa.project.post.model.dto.DebatePostDTO;
import com.jjikmukpa.project.post.model.entity.DebatePost;
import com.jjikmukpa.project.post.repository.DebatePostRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class DebatePostService {

    private final DebatePostRepository debatepostRepository;
    private final ModelMapper modelMapper;
    private final String UPLOAD_DIR = "src/main/resource/static/uploads";

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    // 조회수
    @Transactional
    public void increasePostCount(int debatePostNo) {
        DebatePost debatePost = debatepostRepository.findById(debatePostNo)
                .orElseThrow(() -> new ResourceNotFoundException("게시물을 찾을 수 없습니다."));
        debatePost.incrementPostCount();
        debatepostRepository.save(debatePost);
    }


    // 이미지 저장 메서드
    public String saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            // 현재 시간을 표기해서 중복 방지
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 경로 설정
            Path directoryPath = Paths.get("boarddb/");
            Path path = directoryPath.resolve(fileName);

            // 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            //파일 저장
            Files.write(path, file.getBytes());

            return "/boarddb/" + fileName; // 저장된 이미지 경로 반환
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDebatePost(DebatePost debatePost) {
        String sql = "INSERT INTO boarddb.debate_post (post_title, content, post_count, imagePath1, imagePath2) VALUES (?, ?,  ?, ?,?)";
        jdbcTemplate.update(sql, debatePost.getPostTitle(), debatePost.getContent(),debatePost.getPostCount(), debatePost.getImagePath1(), debatePost.getImagePath2());
    }


}