package com.xebec.BusTracking.controller;

import com.xebec.BusTracking.dto.SignupDto;
import com.xebec.BusTracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupDto signupDto) {
        if(!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        userService.signup(signupDto);
    }
}