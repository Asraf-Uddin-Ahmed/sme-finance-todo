package com.smefinance.todo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.smefinance.todo.contants.PriorityType;
import com.smefinance.todo.contants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;

@DataJpaTest
class TodoTaskRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoTaskRepository repository;

    private TodoTask getTodoTask(TaskStatus taskStatus) {
    	TodoTask todoTask = new TodoTask();
    	todoTask.setDescription("test description");
    	todoTask.setPriority(PriorityType.MEDIUM);
    	todoTask.setStatus(taskStatus);
    	todoTask.setTitle("test title");
    	return todoTask;
    }
    
    private TodoTask getTodoTask() {
    	return this.getTodoTask(TaskStatus.DOING);
    }
    
	@Test
	void test_save() {
		TodoTask todoTask = this.getTodoTask();
		TodoTask savedData = entityManager.persist(todoTask);
		
		TodoTask foundData = repository.findById(savedData.getId()).get();
		
		assertThat(foundData).isEqualTo(savedData);
	}

	@Test
	void test_findByIdAndIsDeletedFalse() {
		TodoTask todoTask = this.getTodoTask();
		TodoTask savedData = entityManager.persist(todoTask);
		
		TodoTask foundData = repository.findByIdAndIsDeletedFalse(savedData.getId());
		
		assertThat(foundData).isEqualTo(savedData);
	}

	@Test
	void test_findAllBy() {
		TodoTask todoTask = this.getTodoTask(TaskStatus.DOING);
		TodoTask savedData = entityManager.persist(todoTask);
		
		List<TodoTask> foundDataList = repository.findAllBy(TaskStatus.DOING);
		
		assertThat(foundDataList.get(0)).isEqualTo(savedData);
	}
	
}
