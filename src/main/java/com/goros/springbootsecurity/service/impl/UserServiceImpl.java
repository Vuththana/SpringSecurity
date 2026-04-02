package com.goros.springbootsecurity.service.impl;

import com.goros.springbootsecurity.model.entity.User;
import com.goros.springbootsecurity.model.request.UserRequest;
import com.goros.springbootsecurity.model.response.UserResponse;
import com.goros.springbootsecurity.repository.UserRepository;
import com.goros.springbootsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@NullMarked
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserResponse register(UserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User appUser = userRepository.register(request);
        for (String role : request.getRoles()){
            if (role.equals("ROLE_USER")){
                userRepository.insertUserIdAndRoleId(1L, appUser.getUserId());
            }
            if (role.equals("ROLE_ADMIN")){
                userRepository.insertUserIdAndRoleId(2L, appUser.getUserId());
            }
        }
        return modelMapper.map(userRepository.getUserById(appUser.getUserId()), UserResponse.class);
    }
}
