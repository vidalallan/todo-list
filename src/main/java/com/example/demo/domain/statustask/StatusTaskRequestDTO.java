package com.example.demo.domain.statustask;

import java.time.LocalDateTime;

public record StatusTaskRequestDTO(
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean deleted
) {}