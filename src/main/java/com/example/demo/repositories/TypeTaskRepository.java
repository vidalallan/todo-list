package com.example.demo.repositories;

import com.example.demo.domain.typetask.TypeTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TypeTaskRepository extends JpaRepository<TypeTask, UUID> {

    List<TypeTask> findByDeleted(Boolean deleted);
    long count();
    long countByDeleted(Boolean deleted);
}
