package com.example.demo.controller;

import com.example.demo.domain.typetask.TypeTask;
import com.example.demo.domain.typetask.TypeTaskDeletedDTO;
import com.example.demo.domain.typetask.TypeTaskRequestDTO;
import com.example.demo.service.TypeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/typetask")
public class TypeTaskController {

    @Autowired
    private TypeTaskService typeTaskService;

    @PostMapping
    public ResponseEntity<TypeTask> create(@RequestBody TypeTaskRequestDTO typeTaskBody){
        TypeTask typeTask = this.typeTaskService.createTypeTask(typeTaskBody);
        return ResponseEntity.ok().body(typeTask);
    }

    @GetMapping
    public ResponseEntity<List<TypeTask>> getAll(){
        List<TypeTask> listTypeTask  = typeTaskService.findAllTypeTask();
        return ResponseEntity.ok().body(listTypeTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeTask> getTypeTaskById(@PathVariable UUID id){
        TypeTask typeTask = typeTaskService.findById(id);
        return ResponseEntity.ok(typeTask);
    }

    @GetMapping("/deleted/{deleted}")
    public ResponseEntity<List<TypeTask>> getStatusTaskByDeleted(@PathVariable Boolean deleted){
        return ResponseEntity.ok().body(typeTaskService.findByDeleted(deleted));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeTask> update(@PathVariable UUID id, @RequestBody TypeTaskRequestDTO typeTaskBody) {
        try {
            TypeTask updatedTypeTask = typeTaskService.updateTypeTask(id, typeTaskBody);

            return ResponseEntity.ok(updatedTypeTask);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleted/{id}")
    public ResponseEntity<TypeTask> delete(@PathVariable UUID id) {
        try {
            TypeTask deletedTypeTask = typeTaskService.deletedTypeTask(id);

            return ResponseEntity.ok(deletedTypeTask);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<TypeTaskDeletedDTO> countStatusTask() {
        TypeTaskDeletedDTO counts = typeTaskService.countTypeTask();
        return ResponseEntity.ok(counts);
    }
}