package com.example.demo.domain.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime startTask,
        LocalDateTime endTask,
        String statusTaskTitle,
        String typeTaskTitle,
        String userName,
        String userEmail,
        boolean deleted,
        UUID idStatusTask,
        UUID idTypeTask,
        UUID idUserName
) {}
