package com.project.socialnetwork.service.likecomment;

import com.project.socialnetwork.model.LikeComment;

public interface ILikeCommentService {
    LikeComment saveLikeComment(LikeComment likeComment);

    void deleteLikeComment(Long id);

    Iterable<LikeComment> findAllLikeComment();

    LikeComment findLikeCommentById(Long id);

    Iterable<LikeComment> findLikeCommentsByCommentId(Long id);
}
