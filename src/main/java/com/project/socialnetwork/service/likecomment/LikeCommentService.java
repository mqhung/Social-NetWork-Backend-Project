package com.project.socialnetwork.service.likecomment;

import com.project.socialnetwork.model.LikeComment;
import com.project.socialnetwork.repository.ILikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeCommentService implements ILikeCommentService{
    @Autowired
    private ILikeCommentRepository likeCommentRepository;
    @Override
    public LikeComment saveLikeComment(LikeComment likeComment) {
        return likeCommentRepository.save(likeComment);
    }

    @Override
    public void deleteLikeComment(Long id) {
        likeCommentRepository.deleteById(id);
    }

    @Override
    public Iterable<LikeComment> findAllLikeComment() {
        return likeCommentRepository.findAll();
    }

    @Override
    public LikeComment findLikeCommentById(Long id) {
        return likeCommentRepository.getOne(id);
    }

    @Override
    public Iterable<LikeComment> findLikeCommentsByCommentId(Long id) {
        return likeCommentRepository.findLikeCommentsByCommentId(id);
    }
}
