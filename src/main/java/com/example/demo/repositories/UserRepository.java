package com.example.demo.repositories;

import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByDeleted(Boolean deleted);
    long count();
    long countByDeleted(Boolean deleted);

}
