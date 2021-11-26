package com.smefinance.todo.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smefinance.todo.contants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.repositories.TodoTaskRepository;
import com.smefinance.todo.services.TodoTaskService;

@Service
@Transactional
public class TodoTaskServiceImpl implements TodoTaskService {

	private TodoTaskRepository todoTaskRepository;

	@Autowired
	public TodoTaskServiceImpl(TodoTaskRepository todoTaskRepository) {
		this.todoTaskRepository = todoTaskRepository;
	}

	@Override
	public TodoTask save(TodoTask todoTask) {
		todoTask.setStatus(TaskStatus.TODO);
		return this.todoTaskRepository.save(todoTask);
	}

}
