package com.will.loja.model.user;

import com.will.loja.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;

    private String username;

    private Integer companyId;

    private List<Role> roles;
  
}