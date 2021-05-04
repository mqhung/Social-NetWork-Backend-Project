package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public Iterable<AppUser> getAllUser() {
        return userService.findALl();
    }

    @PutMapping("/users/{id}/block")
    public ResponseEntity<AppUser> blockUser(@RequestBody AppUser appUser,
                                          @PathVariable Long id) {
        AppUser user = userService.findById(id);
        user.setBlocked(true);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/unblock")
    public ResponseEntity<AppUser> unblockUser(@RequestBody AppUser appUser,
                                             @PathVariable Long id) {
        AppUser user = userService.findById(id);
        user.setBlocked(false);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
