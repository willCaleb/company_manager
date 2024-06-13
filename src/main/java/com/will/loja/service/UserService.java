package com.will.loja.service;

import com.will.loja.model.dto.UserDTO;
import com.will.loja.model.entity.User;
import com.will.loja.model.user.JwtResponseDTO;
import com.will.loja.repository.UserRepository;
import com.will.loja.service.impl.AbstractService;

public interface UserService extends IAbstractService<User, UserDTO, UserRepository> {

    User include(User user);

    JwtResponseDTO autehnticate(User user);
}
