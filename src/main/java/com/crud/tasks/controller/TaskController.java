package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    //TaskDto taskDto = new TaskDto((long)2,"task_14.5","checking my code");

    @RequestMapping(method = RequestMethod.GET,value = "getTasks")
    public List<TaskDto> getTasks(){
        List<TaskDto> newList = new ArrayList<>();
        newList.add(new TaskDto((long)2,"test_2","test my code"));
        newList.add(new TaskDto((long)3,"test_3","test my code"));
        newList.add(new TaskDto((long)4,"test_4","test my code"));

        return newList;
    }

    @RequestMapping(method = RequestMethod.GET,value = "getTask")
    public TaskDto getTask(String taskId){
        return new TaskDto((long)1, "test title", "test_content");
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