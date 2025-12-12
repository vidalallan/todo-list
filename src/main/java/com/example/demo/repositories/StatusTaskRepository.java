package com.example.demo.repositories;

import com.example.demo.domain.statustask.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StatusTaskRepository extends JpaRepository<StatusTask, UUID> {

    List<StatusTask>findByDeleted(Boolean deleted);

    long count();
    long countByDeleted(Boolean deleted);

}