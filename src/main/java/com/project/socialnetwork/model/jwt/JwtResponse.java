package com.project.socialnetwork.model.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Checker";
    private String username;
    private Collection<? extends GrantedAuthority> roles;
}
