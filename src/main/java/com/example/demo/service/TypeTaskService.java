package com.example.demo.service;

import com.example.demo.domain.typetask.TypeTask;
import com.example.demo.domain.typetask.TypeTaskDeletedDTO;
import com.example.demo.domain.typetask.TypeTaskRequestDTO;
import com.example.demo.repositories.TypeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TypeTaskService {

    @Autowired
    private TypeTaskRepository repository;

    public TypeTask createTypeTask(TypeTaskRequestDTO data){

        TypeTask typeTask = new TypeTask();

        typeTask.setTitle(data.title());
        typeTask.setCreatedAt(LocalDateTime.now());
        typeTask.setUpdatedAt(LocalDateTime.now());
        typeTask.setDeleted(false);

        return repository.save(typeTask);
    }

    public List<TypeTask> findAllTypeTask(){
        return repository.findAll();
    }

    public List<TypeTask> findByDeleted(Boolean deleted){
        return repository.findByDeleted(deleted);
    }

    public TypeTask findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type task not found"));
    }

    public TypeTask updateTypeTask(UUID id, TypeTaskRequestDTO data) {

        Optional<TypeTask> optionalTypeTask = repository.findById(id);

        if (optionalTypeTask.isPresent()) {
            TypeTask typeTask = optionalTypeTask.get();

            typeTask.setTitle(data.title());
            typeTask.setUpdatedAt(LocalDateTime.now());

            return repository.save(typeTask);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public TypeTask deletedTypeTask(UUID id) {

        Optional<TypeTask> optionalTypeTask = repository.findById(id);

        if (optionalTypeTask.isPresent()) {
            TypeTask typeTask = optionalTypeTask.get();

            typeTask.setDeleted(true);
            typeTask.setUpdatedAt(LocalDateTime.now());

            return repository.save(typeTask);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public long count(){
        return repository.count();
    }

    public TypeTaskDeletedDTO countTypeTask(){
        long delTrue = repository.countByDeleted(true);
        long delFalse = repository.countByDeleted(false);
        long total = repository.count();

        return new TypeTaskDeletedDTO(delFalse, delTrue, total);
    }
}