package com.smefinance.todo.services.imp;

import com.smefinance.todo.contants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.repositories.TodoTaskRepository;
import com.smefinance.todo.services.TodoTaskService;
import com.smefinance.todo.utils.ExceptionPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {

    private final TodoTaskRepository todoTaskRepository;

    @Autowired
    public TodoTaskServiceImpl(TodoTaskRepository todoTaskRepository) {
        this.todoTaskRepository = todoTaskRepository;
    }

    @Override
    @Transactional
    public TodoTask saveWithTaskStatus(TodoTask todoTask, TaskStatus taskStatus) {
        todoTask.setStatus(taskStatus);
        return this.todoTaskRepository.save(todoTask);
    }

    @Override
    @Transactional
    public TodoTask save(TodoTask todoTask) {
        return this.todoTaskRepository.save(todoTask);
    }

    @Override
    public TodoTask getById(Integer id) {
        try {
            TodoTask todoTask = todoTaskRepository.findByIdAndIsDeletedFalse(id);
            if (todoTask == null) {
                return ExceptionPreconditions.entityNotFound(TodoTask.class, "id", id.toString());
            }
            return todoTask;
        } catch (NoSuchElementException ex) {
            return ExceptionPreconditions.entityNotFound(TodoTask.class, "id", id.toString());
        }
    }

    @Override
    @Transactional
    public void delete(TodoTask todoTask) {
        todoTask.setDeleted(true);
        this.todoTaskRepository.save(todoTask);
    }

    @Override
    public List<TodoTask> getAllBy(TaskStatus status) {
        return this.todoTaskRepository.findAllBy(status);
    }

}
