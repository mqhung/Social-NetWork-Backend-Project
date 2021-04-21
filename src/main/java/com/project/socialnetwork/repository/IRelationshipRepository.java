package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> findAllBySecondUser(Long id, String status);
}
