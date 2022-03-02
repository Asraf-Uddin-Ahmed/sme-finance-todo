package com.smefinance.todo.services;

import java.util.List;

import com.smefinance.todo.constants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;

public interface TodoTaskService {

	TodoTask save(TodoTask todoTask);

	TodoTask getById(Integer id);

	void delete(TodoTask todoTask);

	TodoTask saveWithTaskStatus(TodoTask todoTask, TaskStatus taskStatus);

	List<TodoTask> getAllBy(TaskStatus status);

}
