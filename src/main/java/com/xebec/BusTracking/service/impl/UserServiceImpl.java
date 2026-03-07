package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.SignupDto;
import com.xebec.BusTracking.exception.EmailAlreadyExistsException;
import com.xebec.BusTracking.model.User;
import com.xebec.BusTracking.model.UserRole;
import com.xebec.BusTracking.repository.UserRepository;
import com.xebec.BusTracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupDto signupDto) throws EmailAlreadyExistsException {
        String email = signupDto.getEmail();
        if(userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("User already exists with given email: " + email);
        }

        User user = User.builder()
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .phoneNumber(signupDto.getPhoneNumber())
                .email(signupDto.getEmail())
                .role(UserRole.PASSENGER)
                .passwordHash(passwordEncoder.encode(signupDto.getPassword()))
                .build();

        userRepository.save(user);
    }
}
