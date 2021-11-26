package com.smefinance.todo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smefinance.todo.contants.TaskStatus;
import com.smefinance.todo.dtos.mapper.TodoTaskMapper;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.services.TodoTaskService;

@RestController
@RequestMapping("/todo-tasks")
public class TodoTaskController {
	
	private TodoTaskService todoTaskService;
	private TodoTaskMapper todoTaskMapper;
	
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
}