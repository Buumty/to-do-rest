package com.wojciechandrzejczak.to_do_rest;

import com.sun.source.util.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<URI> save(@RequestBody Task task) {
        taskService.save(task);

        String location = "http://localhost:8080/tasks/" + task.getId();
        URI locationUri = URI.create(location);

        return ResponseEntity.created(locationUri).body(locationUri);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        taskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
