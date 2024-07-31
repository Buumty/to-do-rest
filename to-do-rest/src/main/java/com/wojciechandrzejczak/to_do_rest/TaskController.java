package com.wojciechandrzejczak.to_do_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskService.findAll();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Integer id) {
        Task task = taskService.findById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<URI> save(@RequestBody @NonNull Task task) {
        Task savedTask = taskService.save(task);

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();

        return ResponseEntity.created(locationUri).body(locationUri);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> update(@PathVariable Integer id, @RequestBody @NonNull Task task) {
        taskService.update(id, task);
        Task updatedTask = taskService.findById(id);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updatePartially(@PathVariable Integer id, @RequestBody @NonNull Task task) {
        taskService.updatePartially(id, task);
        Task updatedTask = taskService.findById(id);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        taskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
