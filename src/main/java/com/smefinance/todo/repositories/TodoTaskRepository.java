package com.smefinance.todo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.smefinance.todo.entities.TodoTask;

@Repository
public interface TodoTaskRepository extends PagingAndSortingRepository<TodoTask, Integer> {
	
}
