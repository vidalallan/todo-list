package com.example.demo.domain.typetask;

import java.time.LocalDateTime;

public record TypeTaskRequestDTO(
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean deleted
) {}