package com.smefinance.todo.dtos.mapper;

import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.entities.TodoTask;

public interface TodoTaskMapper {

	TodoTask getEntity(TodoTaskRequest request);

}