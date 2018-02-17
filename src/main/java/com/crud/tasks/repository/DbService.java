package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(final String taskId){
        return repository.findById(taskId);
    }
}
