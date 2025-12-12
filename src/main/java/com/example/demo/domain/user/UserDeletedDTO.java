package com.example.demo.domain.user;

public record UserDeletedDTO(
   long deletedFalse,
   long deletedTrue,
   long total
) {}
