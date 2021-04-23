package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;
import com.project.socialnetwork.service.relationship.IRelationshipService;
import com.project.socialnetwork.service.status.IStatusService;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/relationship")
public class RelationshipController {
    @Autowired
    private IRelationshipService relationshipService;
    @Autowired
    private IStatusService statusService;
    @Autowired
    private IUserService userService;

    @ModelAttribute("listStatus")
    public Iterable<RelationshipStatus> showAll() {
        return statusService.findAllStatus();
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Relationship>> getAll() {
        return new ResponseEntity<>(relationshipService.findAllRelationship(), HttpStatus.OK);
    }

    @PostMapping("/create/{secondUserId}")
    public ResponseEntity<?> sendFriendRequest(@PathVariable Long secondUserId) {
        AppUser currentUser = userService.getCurrentUser();
        Relationship relationship = this.checkRelationship(currentUser.getId(), secondUserId);
        if (relationship == null) {
            Relationship newRelationship = new Relationship(currentUser.getId(), secondUserId);
            RelationshipStatus status = statusService.findStatusById(1L);
            newRelationship.setStatus(status);
            newRelationship.setFirstUserId(currentUser.getId());
            newRelationship.setSecondUserId(secondUserId);
            relationshipService.saveRelationship(newRelationship);
            return new ResponseEntity<>("da gui yeu cau ket ban", HttpStatus.OK);
        } else {
            relationship.setStatus(statusService.findStatusById(2L));
            relationshipService.saveRelationship(relationship);
            return new ResponseEntity<>("da la ban be", HttpStatus.OK);
        }
    }

    @GetMapping("/listFriend/{userId}")
    public ResponseEntity<Iterable<AppUser>> findAllFriend(@PathVariable Long userId) {
        Iterable<Relationship> relationships = relationshipService.findAllByFirstUserIdAndStatusOrSecondUserIdAndStatus(userId, statusService.findStatusById(2L), userId, statusService.findStatusById(2L));
        List<AppUser> users = new ArrayList<>();
        for (Relationship relationship : relationships
        ) {
            if (relationship.getFirstUserId().equals(userId)) {
                users.add(userService.findById(relationship.getSecondUserId()));
            } else users.add(userService.findById(relationship.getFirstUserId()));

        }
        return new ResponseEntity<Iterable<AppUser>>(users, HttpStatus.OK);
    }

    public Relationship checkRelationship(Long firstUserId, Long secondUserId) {
        Relationship relationship;
        if (relationshipService.findRelationshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId) != null) {
            relationship = relationshipService.findRelationshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
        } else relationship = null;
        return relationship;
    }


}
