package com.smefinance.todo.dtos.mapper;

import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;

public interface TodoTaskMapper {

	TodoTask getEntity(TodoTaskRequest request);

	TodoTaskResponse getResponse(TodoTask entity);

	void loadEntity(TodoTaskRequest request, TodoTask entity);

}
