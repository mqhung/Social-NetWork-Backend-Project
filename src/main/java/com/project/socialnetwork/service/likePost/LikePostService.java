package com.project.socialnetwork.service.likePost;

import com.project.socialnetwork.model.LikePost;
import com.project.socialnetwork.repository.ILikePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikePostService implements ILikePostService {

    @Autowired
    ILikePostRepository likePostRepository;

    @Override
    public List<LikePost> findALl() {
        return null;
    }

    @Override
    public Page<LikePost> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public LikePost findById(Long id) {
        return null;
    }

    @Override
    public LikePost save(LikePost likePost) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<LikePost> findLikePostByPostId(Long postId) {
        return null;
    }
}
