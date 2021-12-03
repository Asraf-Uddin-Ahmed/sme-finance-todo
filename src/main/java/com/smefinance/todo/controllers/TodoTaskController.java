package com.smefinance.todo.controllers;

import com.smefinance.todo.contants.TaskStatus;
import com.smefinance.todo.dtos.mapper.TodoTaskMapper;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.services.TodoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todo-tasks")
public class TodoTaskController {

    private final TodoTaskService todoTaskService;
    private final TodoTaskMapper todoTaskMapper;

    @Autowired
    public TodoTaskController(TodoTaskService todoTaskService, TodoTaskMapper todoTaskMapper) {
        this.todoTaskService = todoTaskService;
        this.todoTaskMapper = todoTaskMapper;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoTaskResponse createTodoTask(@RequestBody @Valid TodoTaskRequest request) {
        TodoTask todoTask = this.todoTaskMapper.getEntity(request);
        todoTask = this.todoTaskService.saveWithTaskStatus(todoTask, TaskStatus.TODO);
        return this.todoTaskMapper.getResponse(todoTask);
    }

    @PutMapping("/{id}")
    public TodoTaskResponse updateTodoTask(@PathVariable int id, @RequestBody @Valid TodoTaskRequest request) {
        TodoTask todoTask = this.todoTaskService.getById(id);
        this.todoTaskMapper.loadEntity(request, todoTask);
        todoTask = this.todoTaskService.save(todoTask);
        return this.todoTaskMapper.getResponse(todoTask);
    }

    @PutMapping("/{id}/done")
    public TodoTaskResponse makeTodoTaskToDone(@PathVariable int id) {
        TodoTask todoTask = this.todoTaskService.getById(id);
        todoTask = this.todoTaskService.saveWithTaskStatus(todoTask, TaskStatus.DONE);
        return this.todoTaskMapper.getResponse(todoTask);
    }

    @DeleteMapping("/{id}")
    public TodoTaskResponse deleteTodoTask(@PathVariable int id) {
        TodoTask todoTask = this.todoTaskService.getById(id);
        this.todoTaskService.delete(todoTask);
        return this.todoTaskMapper.getResponse(todoTask);
    }

    @GetMapping("")
    public List<TodoTaskResponse> getAllTodoTasks(@RequestParam(value = "status", required = false) TaskStatus status) {
        List<TodoTask> todoTasks = this.todoTaskService.getAllBy(status);
        return this.todoTaskMapper.getResponses(todoTasks);
    }

}