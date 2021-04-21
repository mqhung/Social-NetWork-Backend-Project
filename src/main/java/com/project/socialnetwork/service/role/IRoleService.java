package com.project.socialnetwork.service.role;

import com.project.socialnetwork.model.AppRole;
import com.project.socialnetwork.service.IService;

public interface IRoleService extends IService<AppRole> {
    AppRole findRoleByName(String name);
}
