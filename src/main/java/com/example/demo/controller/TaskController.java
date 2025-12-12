package com.example.demo.controller;

import com.example.demo.domain.task.Task;
import com.example.demo.domain.task.TaskRequestDTO;
import com.example.demo.domain.task.TaskResponseDTO;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService  taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskRequestDTO taskBody){
        Task task = this.taskService.create(taskBody);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.deletedTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody TaskRequestDTO taskBody) {

        try {
            Task updatedTask = taskService.updateTask(id, taskBody);
            return ResponseEntity.ok(taskService.toResponseDTO(updatedTask));

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable UUID id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(taskService.toResponseDTO(task));
    }
}