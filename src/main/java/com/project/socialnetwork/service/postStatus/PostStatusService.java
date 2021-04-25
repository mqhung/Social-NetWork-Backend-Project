package com.project.socialnetwork.service.postStatus;

import com.project.socialnetwork.repository.PostStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostStatusService implements IPostStatusService {

    @Autowired
    PostStatusRepository postStatusRepository;
    @Override
    public List<com.project.socialnetwork.model.PostStatus> findALl() {
        return postStatusRepository.findAll();
    }

    @Override
    public Page<com.project.socialnetwork.model.PostStatus> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public com.project.socialnetwork.model.PostStatus findById(Long id) {
        return null;
    }

    @Override
    public com.project.socialnetwork.model.PostStatus save(com.project.socialnetwork.model.PostStatus postStatus) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
