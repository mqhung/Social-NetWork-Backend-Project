package com.project.socialnetwork.service.relationship;

import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;
import com.project.socialnetwork.repository.IRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService implements IRelationshipService {
    @Autowired
    private IRelationshipRepository relationshipRepository;

    @Override
    public Relationship saveRelationship(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    @Override
    public void deleteRelationship(Long id) {
        relationshipRepository.deleteById(id);
    }

    @Override
    public Iterable<Relationship> findAllRelationship() {
        return relationshipRepository.findAll();
    }

    @Override
    public Relationship findRelationshipById(Long id) {
        return relationshipRepository.findById(id).get();
    }

    @Override
    public Iterable<Relationship> findAllByUserReceiveIdAndStatus(Long userReceiveId, RelationshipStatus status) {
        return relationshipRepository.findAllByUserReceiveIdAndStatus(userReceiveId, status);
    }

    @Override
    public Relationship findRelationshipByUserSendIdAndUserReceiveId(Long userSendId, Long userReceiveId) {
        return relationshipRepository.findRelationshipByUserSendIdAndUserReceiveId(userSendId, userReceiveId);
    }

    @Override
    public Iterable<Relationship> findAllByUserSendIdOrUserReceiveIdAndStatus(Long userSendId, Long userReceiveId, RelationshipStatus status) {
        return relationshipRepository.findAllByUserSendIdOrUserReceiveIdAndStatus(userSendId, userReceiveId, status);
    }

    @Override
    public Iterable<Relationship> findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(Long userSendId, RelationshipStatus firstStatus, Long userReceiveId, RelationshipStatus secondStatus) {
        return relationshipRepository.findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(userSendId, firstStatus, userReceiveId, secondStatus);
    }


}
