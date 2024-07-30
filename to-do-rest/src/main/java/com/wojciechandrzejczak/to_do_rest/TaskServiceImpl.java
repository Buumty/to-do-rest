package com.wojciechandrzejczak.to_do_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Integer id) {
        return taskRepository.findById(id).orElseThrow(InputMismatchException::new);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void update(Integer id, Task updatedtask) {
        taskRepository.findAll().removeIf(t -> t.getId().equals(updatedtask.getId()));
        taskRepository.save(updatedtask);
    }

    @Override
    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
