package com.project.socialnetwork.service.relationship;

import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;

public interface IRelationshipService {
    Relationship saveRelationship(Relationship relationship);

    void deleteRelationship(Long id);

    Iterable<Relationship> findAllRelationship();

    Relationship findRelationshipById(Long id);

    Iterable<Relationship> findAllByUserReceiveIdAndStatus(Long userReceiveId, RelationshipStatus status);

    Relationship findRelationshipByUserSendIdAndUserReceiveId(Long userSendId, Long userReceiveId);

    Iterable<Relationship> findAllByUserSendIdOrUserReceiveIdAndStatus(Long userSendId, Long userReceiveId, RelationshipStatus status);

    Iterable<Relationship> findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(Long userSendId, RelationshipStatus firstStatus, Long userReceiveId, RelationshipStatus secondStatus);

}
