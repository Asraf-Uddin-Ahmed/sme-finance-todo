package com.smefinance.todo.services;

import com.smefinance.todo.entities.TodoTask;

public interface TodoTaskService {

	TodoTask save(TodoTask todoTask);

	TodoTask getById(Integer id);

}
