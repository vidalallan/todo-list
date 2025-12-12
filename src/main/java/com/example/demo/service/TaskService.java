package com.example.demo.service;

import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.domain.task.Task;
import com.example.demo.domain.task.TaskRequestDTO;
import com.example.demo.domain.task.TaskResponseDTO;
import com.example.demo.domain.typetask.TypeTask;
import com.example.demo.domain.user.User;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusTaskService statusTaskService;

    @Autowired
    private TypeTaskService typeTaskService;

    @Autowired
    private UserService userService;

    public Task create(TaskRequestDTO data){

        StatusTask status = statusTaskService.findById(data.statusTaskId());
        TypeTask type = typeTaskService.findById(data.typeTaskId());
        User user = userService.findById(data.userId());


        Task task = new Task();

        task.setTitle(data.title());
        task.setDescription(data.description());

        task.setStartTask(data.startTask());
        task.setEndTask(data.endTask());

       task.setUser(user);
       task.setStatusTask(status);
       task.setTypeTask(type);


        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setDeleted(false);

        return taskRepository.save(task);
    }

    public List<TaskResponseDTO> findAllTasks() {
        return taskRepository.findByDeleted(false)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public List<Task> findByDeleted(Boolean deleted){
        return taskRepository.findByDeleted(deleted);
    }

    public Task deletedTask(UUID id) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            //para apenas inativar a tarefa
            task.setDeleted(true);
            task.setUpdatedAt(LocalDateTime.now());

            return taskRepository.save(task);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public Task updateTask(UUID id, TaskRequestDTO data) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID " + id + " not found."));

        StatusTask status = statusTaskService.findById(data.statusTaskId());
        TypeTask type = typeTaskService.findById(data.typeTaskId());
        User user = userService.findById(data.userId());

        task.setTitle(data.title());
        task.setDescription(data.description());
        task.setStartTask(data.startTask());
        task.setEndTask(data.endTask());
        task.setStatusTask(status);
        task.setTypeTask(type);
        task.setUser(user);

        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }


    public TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartTask(),
                task.getEndTask(),
                task.getStatusTask().getTitle(),
                task.getTypeTask().getTitle(),
                task.getUser().getName(),
                task.getUser().getEmail(),
                task.isDeleted(),
                task.getStatusTask().getId(),
                task.getTypeTask().getId(),
                task.getUser().getId()
        );
    }

    public Task findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}