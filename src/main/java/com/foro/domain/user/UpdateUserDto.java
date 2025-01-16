package com.foro.domain.user;

public record UpdateUserDto(
        String email,
        String password
) {
}
