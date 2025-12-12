package com.example.demo.domain.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequestDTO(
        String title,
        String description,
        LocalDateTime startTask,
        LocalDateTime endTask,
        UUID statusTaskId,
        UUID userId,
        UUID typeTaskId
) {}