package com.goros.springbootsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<String> getAllRoles(Long userId);
}
