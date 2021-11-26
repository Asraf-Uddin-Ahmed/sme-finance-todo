package com.smefinance.todo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smefinance.todo.dtos.mapper.TodoTaskMapper;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.services.TodoTaskService;

import lombok.extern.log4j.*;

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
	public TodoTaskResponse saveTodoTask(@RequestBody @Valid TodoTaskRequest request) {
		TodoTask todoTask = this.todoTaskMapper.getEntity(request);
		todoTask = this.todoTaskService.save(todoTask);
		return this.todoTaskMapper.getResponse(todoTask);
	}

}