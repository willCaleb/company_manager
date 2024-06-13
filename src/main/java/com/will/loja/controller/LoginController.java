package com.will.loja.controller;

import com.will.loja.model.dto.UserDTO;
import com.will.loja.model.user.JwtResponseDTO;
import com.will.loja.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public JwtResponseDTO login(@RequestBody UserDTO userDTO) {
        return userService.autehnticate(userDTO.toEntity());
    }
}
