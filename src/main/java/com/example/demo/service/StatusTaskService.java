package com.example.demo.service;

import com.example.demo.domain.statustask.StatusTaskDeletedDTO;
import com.example.demo.domain.statustask.StatusTaskRequestDTO;
import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.repositories.StatusTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StatusTaskService {

    @Autowired
    private StatusTaskRepository repository;

    public StatusTask createStatusTask(StatusTaskRequestDTO data){

        StatusTask statusTask = new StatusTask();

        statusTask.setTitle(data.title());
        statusTask.setCreatedAt(LocalDateTime.now());
        statusTask.setUpdatedAt(LocalDateTime.now());
        statusTask.setDeleted(false);

        return repository.save(statusTask);
    }

    public List<StatusTask> findAllStatusTasks(){
        return repository.findByDeleted(false);
    }

    public List<StatusTask> findByDeleted(Boolean deleted){
        return repository.findByDeleted(deleted);
    }

    public StatusTask updateStatusTask(UUID id, StatusTaskRequestDTO data) {

        Optional<StatusTask> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()) {
            StatusTask statusTask = optionalTask.get();

            statusTask.setTitle(data.title());
            statusTask.setUpdatedAt(LocalDateTime.now());

            return repository.save(statusTask);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public StatusTask findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StatusTask não encontrado"));
    }


    public StatusTask deletedStatusTask(UUID id) {

        Optional<StatusTask> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()) {
            StatusTask statusTask = optionalTask.get();

            //para apenas inativar o status
            statusTask.setDeleted(true);
            statusTask.setUpdatedAt(LocalDateTime.now());

            return repository.save(statusTask);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public long count(){
        return repository.count();
    }

    public StatusTaskDeletedDTO countStatusTask(){
        long delTrue = repository.countByDeleted(true);
        long delFalse = repository.countByDeleted(false);
        long total = repository.count();

        return new StatusTaskDeletedDTO(delFalse, delTrue, total);
    }
}