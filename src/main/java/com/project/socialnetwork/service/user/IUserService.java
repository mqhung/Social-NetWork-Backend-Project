package com.project.socialnetwork.service.user;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.service.IService;

public interface IUserService extends IService<AppUser> {
    AppUser findUserByUsername(String username);
}
