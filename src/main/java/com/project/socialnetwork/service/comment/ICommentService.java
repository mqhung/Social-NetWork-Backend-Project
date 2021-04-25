package com.project.socialnetwork.service.comment;

import com.project.socialnetwork.model.Comment;
import com.project.socialnetwork.service.IService;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    List<Comment> findCommentByPostId(Long postId);
}
