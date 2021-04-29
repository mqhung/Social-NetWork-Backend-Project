package com.project.socialnetwork.service.likePost;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.LikePost;
import com.project.socialnetwork.model.Post;
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
        return likePostRepository.findAll();
    }

    @Override
    public Page<LikePost> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public LikePost findById(Long id) {
        return likePostRepository.findById(id).get();
    }

    @Override
    public LikePost save(LikePost likePost) {
        return likePostRepository.save(likePost);
    }

    @Override
    public void deleteById(Long id) {
        likePostRepository.deleteById(id);
    }

    @Override
    public List<LikePost> findLikePostByPostId(Long postId) {
        return likePostRepository.findLikePostsByPostId(postId);
    }

//    @Override
//    public LikePost findByPostAndUser(Post post, AppUser user) {
//        return likePostRepository.findByPostAndUser(post, user);
//    }

//    @Override
//    public int countAllByPost(Post post) {
//        return likePostRepository.countAllByPost(post);
//    }

//    @Override
//    public List<LikePost> findAllByPost(Post post) {
//        return likePostRepository.findAllByPost(post);
//    }

//    @Override
//    public void deleteLoveByPostId(Long id) {
//        likePostRepository.deleteLoveByPostId(id);
//    }
}
