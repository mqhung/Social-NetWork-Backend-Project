package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
    List<LikePost> findLikePostsByPostId(Long postId);
}
