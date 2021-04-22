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
    public Iterable<Relationship> findAllBySecondUserIdAndStatus(Long id, RelationshipStatus status) {
        return relationshipRepository.findAllBySecondUserIdAndStatus(id, status);
    }

    @Override
    public Relationship findRelationshipByFirstUserIdAndSecondUserId(Long firstUserId, Long secondUserId) {
        return relationshipRepository.findRelationshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
//    return relationshipRepository.findRelationship(firstUserId,secondUserId);
    }

    @Override
    public Iterable<Relationship> findAllByFirstUserIdOrSecondUserIdAndStatus(Long firstUserId, Long secondUserId, RelationshipStatus status) {
        return relationshipRepository.findAllByFirstUserIdOrSecondUserIdAndStatus(firstUserId, secondUserId, status);
    }

    @Override
    public Iterable<Relationship> findAllByFirstUserIdAndStatusOrSecondUserIdAndStatus(Long firstUserId, RelationshipStatus firstStatus, Long secondUserId, RelationshipStatus secondStatus) {
        return relationshipRepository.findAllByFirstUserIdAndStatusOrSecondUserIdAndStatus(firstUserId, firstStatus, secondUserId, secondStatus);
    }
}
