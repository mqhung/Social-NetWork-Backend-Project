package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.Relationship;
import com.project.socialnetwork.model.RelationshipStatus;
import com.project.socialnetwork.service.relationship.IRelationshipService;
import com.project.socialnetwork.service.status.IStatusService;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Iterable<RelationshipStatus> showAll(){
        return statusService.findAllStatus();
    }
    @GetMapping("/")
    public ResponseEntity<Iterable<Relationship>> getAll(){
        return new ResponseEntity<>(relationshipService.findAllRelationship(), HttpStatus.OK);
    }



}
