package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.LikePost;
import com.project.socialnetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
    List<LikePost> findLikePostsByPostId(Long postId);

//    int countAllByPost(Post post);

//    @Modifying
//    @Query("DELETE from LikePost l where l.post.id = ?1")
//    void deleteLoveByPostId(Long id);

//    LikePost findByPostAndUser(Post post, AppUser user);
//
//    List<LikePost> findAllByPost(Post post);
}
