package com.example.demo.repositories;

import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByDeleted(Boolean deleted);

    long count();
    long countByDeleted(Boolean deleted);

}
