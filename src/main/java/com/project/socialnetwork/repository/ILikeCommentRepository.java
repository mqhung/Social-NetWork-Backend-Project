package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Iterable<LikeComment> findLikeCommentsByCommentId(Long commentId);
}
