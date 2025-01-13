package com.foro.domain.user;

import jakarta.validation.constraints.NotBlank;

public record SingupUserDto(

        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
