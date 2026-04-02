package com.goros.springbootsecurity.service;

import com.goros.springbootsecurity.model.request.UserRequest;
import com.goros.springbootsecurity.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponse register(UserRequest request);
}
