package com.project.socialnetwork.service.likePost;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.LikePost;
import com.project.socialnetwork.model.Post;
import com.project.socialnetwork.service.IService;

import java.util.List;

public interface ILikePostService extends IService<LikePost> {
    List<LikePost> findLikePostByPostId(Long postId);
    LikePost findByPostAndUser (Post post, AppUser user);
    int countAllByPost(Post post);
    List<LikePost> findAllByPost(Post post);
    void deleteLoveByPostId(Long id);
}
