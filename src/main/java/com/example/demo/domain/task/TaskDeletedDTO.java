package com.example.demo.domain.task;

public record TaskDeletedDTO(
   long deletedFalse,
   long deletedTrue,
   long total
) {}