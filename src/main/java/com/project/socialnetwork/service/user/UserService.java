package com.project.socialnetwork.service.user;

import com.project.socialnetwork.model.AppRole;
import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.jwt.UserPrinciple;
import com.project.socialnetwork.repository.UserRepository;
import com.project.socialnetwork.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<AppUser> findALl() {
        return userRepository.findAll();
    }

    @Override
    public Page<AppUser> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public AppUser save(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        if (appUser.getRoles() == null) {
            AppRole role = roleService.findRoleByName("ROLE_USER");
            Set<AppRole> roles = new HashSet<>();
            roles.add(role);
            appUser.setRoles(roles);
        }
        return userRepository.save(appUser);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = userRepository.findAppUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(user);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsAppUserByUsername(username);
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.findUserByUsername(userName);
        return user;
    }


}
