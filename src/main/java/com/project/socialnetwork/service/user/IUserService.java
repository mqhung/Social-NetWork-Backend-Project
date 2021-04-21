package com.project.socialnetwork.service.user;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IService<AppUser>, UserDetailsService {
    AppUser findUserByUsername(String username);
    UserDetails loadUserByUsername(String username);
    boolean existsUserByUsername(String username);
}