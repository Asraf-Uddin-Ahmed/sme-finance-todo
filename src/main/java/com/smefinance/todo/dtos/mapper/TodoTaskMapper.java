package com.smefinance.todo.dtos.mapper;

import java.util.List;

import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;

public interface TodoTaskMapper {

	TodoTask getEntity(TodoTaskRequest request);

	TodoTaskResponse getResponse(TodoTask entity);

	void loadEntity(TodoTaskRequest request, TodoTask entity);

	List<TodoTaskResponse> getResponses(List<TodoTask> entities);

}
