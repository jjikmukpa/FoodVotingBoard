package com.jjikmukpa.project.post.repository;

import com.jjikmukpa.project.post.model.entity.DebatePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebatePostRepository extends JpaRepository<DebatePost, Integer> {
}
