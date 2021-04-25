package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatusRepository extends JpaRepository<PostStatus, Long> {
}
