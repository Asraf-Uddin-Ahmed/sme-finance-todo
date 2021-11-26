package com.smefinance.todo.services.imp;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smefinance.todo.contants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.repositories.TodoTaskRepository;
import com.smefinance.todo.services.TodoTaskService;
import com.smefinance.todo.utils.ExceptionPreconditions;

@Service
@Transactional
public class TodoTaskServiceImpl implements TodoTaskService {

	private TodoTaskRepository todoTaskRepository;

	@Autowired
	public TodoTaskServiceImpl(TodoTaskRepository todoTaskRepository) {
		this.todoTaskRepository = todoTaskRepository;
	}

	@Override
	public TodoTask create(TodoTask todoTask) {
		todoTask.setStatus(TaskStatus.TODO);
		return this.todoTaskRepository.save(todoTask);
	}

	@Override
	public TodoTask save(TodoTask todoTask) {
		return this.todoTaskRepository.save(todoTask);
	}

	@Override
	public TodoTask getById(Integer id) {
		try {
			TodoTask todoTask = todoTaskRepository.findByIdAndIsDeletedFalse(id);
			if(todoTask == null) {
				return ExceptionPreconditions.entityNotFound(TodoTask.class, "id", id.toString());	
			}
			return todoTask;
		} catch (NoSuchElementException nseex) {
			return ExceptionPreconditions.entityNotFound(TodoTask.class, "id", id.toString());
		}
	}
	
	@Override
	public void delete(TodoTask todoTask) {
		todoTask.setDeleted(true);
		this.todoTaskRepository.save(todoTask);
	}
}
