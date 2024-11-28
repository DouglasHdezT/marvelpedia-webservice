package me.douglashdezt.simanmarvelpediaws.services.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.dtos.SaveUserDTO;
import me.douglashdezt.simanmarvelpediaws.models.User;
import me.douglashdezt.simanmarvelpediaws.repositories.UserRepository;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void createUser(SaveUserDTO info) {
        User user = modelMapper.map(info, User.class);
        user.setPassword(passwordEncoder.encode(info.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findAuthenticated() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new AccessDeniedException("User not authenticated"));
    }

    @Override
    public boolean verifyPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
