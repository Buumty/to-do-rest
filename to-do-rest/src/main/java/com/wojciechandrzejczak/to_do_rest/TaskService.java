package com.wojciechandrzejczak.to_do_rest;



import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> findAll();
    Task findById(Integer id);
    Task save(Task task);
    void update(Integer id, Task task);
    void updatePartially(Integer id, Task task);
    void deleteById(Integer id);
}
