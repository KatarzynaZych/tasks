package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.DbService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET,value = "getTasks")
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET,value = "getTask")
    public TaskDto getTask(String taskId){
         return taskMapper.mapToTaskDto(service.getTaskById(taskId));
        //return new TaskDto((long)1, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "delete")
    public void delete(String taskId){
        System.out.println("The task has been deleted");
    }

    @RequestMapping(method = RequestMethod.PUT,value = "update")
    public TaskDto update( TaskDto taskDto){
        return new TaskDto((long)1,"Edited test title","Test content");
    }

    @RequestMapping(method = RequestMethod.POST,value = "createTask")
    public void createTask( TaskDto taskDto){
        }
}