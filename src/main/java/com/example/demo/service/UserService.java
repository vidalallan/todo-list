package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDeletedDTO;
import com.example.demo.domain.user.UserRequestDTO;
import com.example.demo.domain.user.UserResponseDTO;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO create(UserRequestDTO data){

        User user = new User();
        user.setName((data.name()));
        user.setEmail(data.email());
        user.setPassword(data.password());
        user.setPicture(data.picture());
        
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDeleted(false);

        User savedUser = userRepository.save(user);

        return mapToUserResponseDTO(savedUser);
    }

    // UserService.java
    public List<UserResponseDTO> findAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponseDTO)
                .collect(Collectors.toList());
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type task not found"));
    }

    public List<User> findByDeleted(Boolean deleted){
        return userRepository.findByDeleted(deleted);
    }

    public User updateUser(UUID id, UserRequestDTO data) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setName(data.name());
            user.setEmail(data.email());
            user.setPassword(data.password());
            user.setPicture(data.picture());

            user.setUpdatedAt(LocalDateTime.now());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public User deletedUser(UUID id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //para apenas inativar o usuário
            user.setDeleted(true);
            user.setUpdatedAt(LocalDateTime.now());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("ID " + id + " not found.");
        }
    }

    public long count(){
        return userRepository.count();
    }

    public UserDeletedDTO countUser(){
        long delTrue = userRepository.countByDeleted(true);
        long delFalse = userRepository.countByDeleted(false);
        long total = userRepository.count();

        return new UserDeletedDTO(delFalse, delTrue, total);
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPicture(),
                user.isDeleted()
        );
    }

}
