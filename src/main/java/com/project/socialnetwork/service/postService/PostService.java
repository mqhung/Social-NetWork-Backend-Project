package com.project.socialnetwork.service.postService;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Post;
import com.project.socialnetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findALl() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
    postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllByAppUser(AppUser user) {
        return postRepository.findAllByAppUser(user);
    }
}
