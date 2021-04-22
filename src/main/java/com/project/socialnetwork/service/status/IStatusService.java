package com.project.socialnetwork.service.status;

import com.project.socialnetwork.model.RelationshipStatus;

public interface IStatusService {
    RelationshipStatus save(RelationshipStatus status);

    void deleteStatus(Long id);

    Iterable<RelationshipStatus> findAllStatus();

    RelationshipStatus findStatusById(Long id);
}
