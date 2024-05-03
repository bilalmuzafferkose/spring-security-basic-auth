package com.bilalkose.springsecuritybasicauth.dto;

import com.bilalkose.springsecuritybasicauth.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest (
        String name,
        String username,
        String password,
        Set<Role> authorities
){

}
