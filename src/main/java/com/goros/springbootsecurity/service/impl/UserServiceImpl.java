package com.goros.springbootsecurity.service.impl;

import com.goros.springbootsecurity.repository.UserRepository;
import com.goros.springbootsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUser(email);
    }

    @Override
    public List<String> getAllRoles(Long userId) {
        return userRepository.getAllRoles(userId);
    }
}
