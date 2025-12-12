package com.example.demo.domain.statustask;

public record StatusTaskDeletedDTO(
   long deletedFalse,
   long deletedTrue,
   long total
) {}
