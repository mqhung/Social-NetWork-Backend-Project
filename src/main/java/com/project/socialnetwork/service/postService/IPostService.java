package com.project.socialnetwork.service.postService;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Post;
import com.project.socialnetwork.service.IService;

import java.util.List;

public interface IPostService extends IService<Post> {
    List<Post> findAllByAppUser(AppUser user);

}
