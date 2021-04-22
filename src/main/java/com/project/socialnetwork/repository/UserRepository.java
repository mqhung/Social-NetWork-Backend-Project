package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
    boolean existsAppUserByUsername(String username);
}
