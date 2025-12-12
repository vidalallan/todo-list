package com.example.demo.controller;

import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.domain.statustask.StatusTaskDeletedDTO;
import com.example.demo.domain.statustask.StatusTaskRequestDTO;
import com.example.demo.service.StatusTaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/statustask")
public class StatusTaskController {

    @Autowired
    private StatusTaskService statusTaskService;

    @PostMapping
    public ResponseEntity<StatusTask> create(@RequestBody StatusTaskRequestDTO statusTaskBody){
        StatusTask statusTask = this.statusTaskService.createStatusTask(statusTaskBody);
        return ResponseEntity.ok().body(statusTask);
    }

    @GetMapping
    public ResponseEntity<List<StatusTask>> getAll(){
        List<StatusTask> listStatutsTask  = statusTaskService.findAllStatusTasks();
        return ResponseEntity.ok().body(listStatutsTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusTask> getStatusTaskById(@PathVariable UUID id){
        StatusTask status = statusTaskService.findById(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/deleted/{deleted}")
    public ResponseEntity<List<StatusTask>> getStatusTaskByDeleted(@PathVariable Boolean deleted){
        return ResponseEntity.ok().body(statusTaskService.findByDeleted(deleted));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusTask> update(@PathVariable UUID id, @RequestBody StatusTaskRequestDTO statusTaskBody) {
        try {
            StatusTask updatedTask = statusTaskService.updateStatusTask(id, statusTaskBody);

            return ResponseEntity.ok(updatedTask);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleted/{id}")
    public ResponseEntity<StatusTask> delete(@PathVariable UUID id) {
        try {
            StatusTask deletedTask = statusTaskService.deletedStatusTask(id);

            return ResponseEntity.ok(deletedTask);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<StatusTaskDeletedDTO> countStatusTask() {
        StatusTaskDeletedDTO counts = statusTaskService.countStatusTask();
        return ResponseEntity.ok(counts);
    }
}