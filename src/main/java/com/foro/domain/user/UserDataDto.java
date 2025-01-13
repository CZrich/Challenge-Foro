package com.foro.domain.user;

public record UserDataDto(
        Long id,
        String email

) {
    public UserDataDto(User user){
        this(user.getId(),user.getEmail());
    }
}
