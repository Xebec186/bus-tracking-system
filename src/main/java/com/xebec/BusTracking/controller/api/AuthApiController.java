package com.xebec.BusTracking.controller.api;

import com.xebec.BusTracking.dto.SignupDto;
import com.xebec.BusTracking.exception.PasswordsDoNotMatchException;
import com.xebec.BusTracking.service.UserService;
import com.xebec.BusTracking.service.impl.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupDto signupDto) {
        if(!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }
        userService.signup(signupDto);
        return jwtService.generateToken(signupDto.getEmail());
    }
}