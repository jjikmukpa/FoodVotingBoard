package com.jjikmukpa.project.post.repository;

import com.jjikmukpa.project.post.model.entity.DebatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface DebatePostRepository extends JpaRepository<DebatePost, Integer> {

    Page<DebatePost> findByPostTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
