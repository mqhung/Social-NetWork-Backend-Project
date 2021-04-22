package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.RelationshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<RelationshipStatus, Long> {
}
