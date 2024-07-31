package com.wojciechandrzejczak.to_do_rest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final DataBaseResetService dataBaseResetService;

    public TaskServiceImpl(TaskRepository taskRepository, DataBaseResetService dataBaseResetService) {
        this.taskRepository = taskRepository;
        this.dataBaseResetService = dataBaseResetService;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id + " was not found!"));
    }

    @Transactional
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public void update(Integer id, Task updatedtask) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id + " was not found!"));

        existingTask.setName(updatedtask.getName());
        existingTask.setStatus(updatedtask.getStatus());

        taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void updatePartially(Integer id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id + " was not found!"));
        if (updatedTask.getName() != null) {
            existingTask.setName(updatedTask.getName());
        }
        if (updatedTask.getStatus() != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }

        taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with id: " + id + " was not found!");
        }

        taskRepository.deleteById(id);
        dataBaseResetService.resetAutoIncrement();
    }
}
