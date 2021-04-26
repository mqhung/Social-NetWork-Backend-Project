package com.project.socialnetwork.service.comment;

import com.project.socialnetwork.model.Comment;
import com.project.socialnetwork.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    public CommentRepository commentRepository;

    @Override
    public List<Comment> findALl() {
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {

    }
    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        return commentRepository.findCommentByPostId(postId);
    }
}
