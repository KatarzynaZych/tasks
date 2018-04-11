package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTasks() throws Exception {
        //given
        List<TaskDto> taskList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskList);

        //when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldFetchTasks() throws Exception {
        //given
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(new TaskDto(1L,"Task One","Test"));
        taskList.add(new TaskDto(2L,"Task Two","Test"));
        taskList.add(new TaskDto(3L,"Task Three","Test"));
        taskList.add(new TaskDto(4L,"Task Four","Test"));

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskList);

       // when(service.getAllTasks()).thenReturn(taskList);

        //when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(4)))
                .andExpect(jsonPath("$[0].title",is("Task One")))
                .andExpect(jsonPath("$[0].content",is("Test")))
                .andExpect(jsonPath("$[1].title",is("Task Two")))
                .andExpect(jsonPath("$[1].content",is("Test")))
                .andExpect(jsonPath("$[2].title",is("Task Three")))
                .andExpect(jsonPath("$[3].content",is("Test")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //given
        Task task = new Task(1L, "Task One", "Test");
        TaskDto taskDto = new TaskDto(1L, "Task One", "Test");

        when(service.getTask(1L)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //when & then
        mockMvc.perform(get("/v1/task/getTask?id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task One")))
                .andExpect(jsonPath("$.content", is("Test")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        Task task1 = new Task(1L, "Task One", "Test");
        TaskDto task1Dto = new TaskDto(1L, "Task One", "Test");

        when(taskMapper.mapToTaskDto(task1)).thenReturn(task1Dto);

        //when & then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
               // .andExpect(jsonPath("$.id", isEmptyOrNullString()));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //given
        Task task = new Task(1L, "Task One", "Test");
        TaskDto taskDto = new TaskDto(1L, "Task One", "Test");
        Task taskNew = new Task(1L, "Task Updated", "Test");
        TaskDto taskDtoNew = new TaskDto(1L, "Task Updated", "Test");

        when(taskMapper.mapToTaskDto(service.saveTask(task))).thenReturn(taskDtoNew);
        //when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Task Updated")))
                .andExpect(jsonPath("$.content", is("Test")));

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task One", "Test");
        TaskDto taskDto = new TaskDto(1L, "Task One", "Test");

        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}