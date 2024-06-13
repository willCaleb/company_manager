package com.will.loja.controller;

import com.will.loja.config.auth.JwtService;
import com.will.loja.model.dto.UserDTO;
import com.will.loja.model.entity.User;
import com.will.loja.model.user.JwtResponseDTO;
import com.will.loja.repository.UserRepository;
import com.will.loja.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends AbstractController<User, UserDTO> {

    private final UserService userService;

    @PostMapping
    public UserDTO include(@RequestBody UserDTO userDTO) {
        return userService.include(userDTO.toEntity()).toDto();
    }

}
