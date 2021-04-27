package com.project.socialnetwork.service.likePost;

import com.project.socialnetwork.model.LikePost;
import com.project.socialnetwork.service.IService;

import java.util.List;

public interface ILikePostService extends IService<LikePost> {
    List<LikePost> findLikePostByPostId(Long postId);
}
