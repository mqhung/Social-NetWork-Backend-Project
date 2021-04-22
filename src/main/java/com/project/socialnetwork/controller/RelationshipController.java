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


}
