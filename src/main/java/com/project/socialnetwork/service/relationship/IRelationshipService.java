package com.project.socialnetwork.service.relationship;

import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;

public interface IRelationshipService {
    Relationship saveRelationship(Relationship relationship);

    void deleteRelationship(Long id);

    Iterable<Relationship> findAllRelationship();

    Relationship findRelationshipById(Long id);

    Iterable<Relationship> findAllBySecondUserIdAndStatus(Long id, RelationshipStatus status);

    Relationship findRelationshipByFirstUserIdAndSecondUserId(Long firstUserId, Long secondUserId);

    Iterable<Relationship> findAllByFirstUserIdOrSecondUserIdAndStatus(Long firstUserId, Long secondUserId, RelationshipStatus status);

    Iterable<Relationship> findAllByFirstUserIdAndStatusOrSecondUserIdAndStatus(Long firstUserId, RelationshipStatus firstStatus, Long secondUserId, RelationshipStatus secondStatus);

}
