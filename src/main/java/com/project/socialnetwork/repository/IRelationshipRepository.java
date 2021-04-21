package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> findAllBySecondUserIdAndStatus(Long id, RelationshipStatus status);
    Relationship findRelationshipByFirstUserIdAndSecondUserId(Long firstUserId, Long secondUserId);
    Iterable<Relationship> findAllByFirstUserIdOrSecondUserIdAndStatus(Long firstUserId, Long secondUserId, RelationshipStatus status);
    Iterable<Relationship> findAllByFirstUserIdAndStatusOrSecondUserIdAndStatus(Long firstUserId, RelationshipStatus firstStatus, Long secondUserId, RelationshipStatus secondStatus);
}
