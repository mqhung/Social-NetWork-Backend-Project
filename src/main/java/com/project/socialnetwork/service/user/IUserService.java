package com.project.socialnetwork.service.user;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends IService<AppUser>, UserDetailsService {
    AppUser findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    AppUser getCurrentUser();
    List<AppUser> searchUserByName(String name);
}
