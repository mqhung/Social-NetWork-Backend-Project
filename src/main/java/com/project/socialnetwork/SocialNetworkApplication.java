package com.project.socialnetwork;

import com.project.socialnetwork.model.AppRole;
import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.service.role.IRoleService;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SocialNetworkApplication {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }
    @PostConstruct
    public void init() {
        List<AppUser> users = (List<AppUser>) userService.findALl();
        List<AppRole> roleList = (List<AppRole>) roleService.findALl();
        if (roleList.isEmpty()) {
            AppRole roleAdmin = new AppRole();
            roleAdmin.setId(1L);
            roleAdmin.setName("ROLE_ADMIN");
            roleService.save(roleAdmin);
            AppRole roleUser = new AppRole();
            roleUser.setId(2L);
            roleUser.setName("ROLE_USER");
            roleService.save(roleUser);
        }
        if (users.isEmpty()) {
            AppUser admin = new AppUser();
            Set<AppRole> roles = new HashSet<>();
            AppRole roleAdmin = new AppRole();
            roleAdmin.setId(1L);
            roleAdmin.setName("ROLE_ADMIN");
            roles.add(roleAdmin);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setBlocked(false);
            admin.setRoles(roles);
            userService.save(admin);
        }

    }
}
