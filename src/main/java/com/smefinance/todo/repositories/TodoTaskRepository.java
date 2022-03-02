package com.smefinance.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smefinance.todo.constants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;

@Repository
public interface TodoTaskRepository extends PagingAndSortingRepository<TodoTask, Integer> {
	TodoTask findByIdAndIsDeletedFalse(int id);
	
	@Query("SELECT tt FROM TodoTask tt "
			+ "WHERE tt.isDeleted=FALSE "
			+ "AND (:status IS NULL OR status=:status) "
			+ "ORDER BY tt.priority DESC")
	List<TodoTask> findAllBy(@Param("status") TaskStatus status);
}
