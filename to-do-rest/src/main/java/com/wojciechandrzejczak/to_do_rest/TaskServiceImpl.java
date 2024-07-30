package com.wojciechandrzejczak.to_do_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String checkStatusById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(InputMismatchException::new);
        return task.getStatus();
    }

    @Override
    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
