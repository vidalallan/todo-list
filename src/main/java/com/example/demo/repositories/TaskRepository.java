package com.example.demo.repositories;

import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.domain.task.Task;
import com.example.demo.domain.task.TaskStatusCountDTO;
import com.example.demo.domain.task.TaskTypeCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByDeleted(Boolean deleted);

    long count();
    long countByDeleted(Boolean deleted);

    //Total de Tarefas por Tipo
    @Query("SELECT new com.example.demo.domain.task.TaskTypeCountDTO(t.typeTask.title, COUNT(t)) " +
            "FROM Task t " +
            "WHERE t.deleted = false " +
            "GROUP BY t.typeTask.title")
    List<TaskTypeCountDTO> countTasksByType();

    //Total de Tarefas por Status
    @Query("SELECT new com.example.demo.domain.task.TaskStatusCountDTO(t.statusTask.title, COUNT(t)) " +
            "FROM Task t " +
            "WHERE t.deleted = false " +
            "GROUP BY t.statusTask.title")
    List<TaskStatusCountDTO> countTasksByStatus();

}
