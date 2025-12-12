package com.example.demo.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String picture,
        Boolean deleted
) {}
