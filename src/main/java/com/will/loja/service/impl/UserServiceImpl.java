package com.will.loja.service.impl;

import com.will.loja.config.auth.JwtService;
import com.will.loja.exception.CustomException;
import com.will.loja.model.dto.UserDTO;
import com.will.loja.model.entity.User;
import com.will.loja.model.user.JwtResponseDTO;
import com.will.loja.repository.UserRepository;
import com.will.loja.service.UserService;
import com.will.loja.utils.DateUtils;
import com.will.loja.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractService<User, UserDTO, UserRepository> implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;



    @Override
    public User include(User user) {

        user.setInclusionDate(DateUtils.getDate());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (Utils.isEmpty(user.getUsername()) || Utils.isEmpty(user.getPassword())) {
            throw new CustomException("Os campos username e password devem ser informados");
        }

        return userRepository.save(user);
    }

    public JwtResponseDTO autehnticate(User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.createToken(authentication))
                    .companyId(1)
                    .username(user.getUsername())
                    .build();
        }
        return null;
    }

}
