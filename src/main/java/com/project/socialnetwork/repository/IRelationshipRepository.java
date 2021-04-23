package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> findAllByUserReceiveIdAndStatus(Long userReceiveId, RelationshipStatus status);
    Relationship findRelationshipByUserSendIdAndUserReceiveId(Long userSendId, Long userReceiveId);
    Iterable<Relationship> findAllByUserSendIdOrUserReceiveIdAndStatus(Long userSendId, Long userReceiveId, RelationshipStatus status);
    Iterable<Relationship> findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(Long userSendId, RelationshipStatus firstStatus, Long userReceiveId, RelationshipStatus secondStatus);

//    @Query(value = "select * from relationship where first_user_id = ? and second_user_id = ?", nativeQuery = true)
//    Relationship findRelationship(Long id1, Long id2);
}
