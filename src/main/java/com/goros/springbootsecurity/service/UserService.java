package com.goros.springbootsecurity.service;

import com.goros.springbootsecurity.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<String> getAllRoles(Long userId);
    User getUserById(Long userId);
}
