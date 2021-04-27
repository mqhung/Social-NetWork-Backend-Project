package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
    Iterable<LikePost> findLikePostsByPostId(Long postId);
}
