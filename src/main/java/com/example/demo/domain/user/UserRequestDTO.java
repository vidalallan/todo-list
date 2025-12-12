package com.example.demo.domain.user;

import java.time.LocalDateTime;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        String picture,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean deleted
) {}
