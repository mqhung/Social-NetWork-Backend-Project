package com.project.socialnetwork.service.status;

import com.project.socialnetwork.model.RelationshipStatus;
import com.project.socialnetwork.repository.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService implements IStatusService{
    @Autowired
    private IStatusRepository statusRepository;
    @Override
    public RelationshipStatus save(RelationshipStatus status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Iterable<RelationshipStatus> findAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public RelationshipStatus findStatusById(Long id) {
        return statusRepository.findById(id).get();
    }
}
