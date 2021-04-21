package com.project.socialnetwork.service.user;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.jwt.UserPrinciple;
import com.project.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

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
}
