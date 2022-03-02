package com.smefinance.todo.repositories;

import com.smefinance.todo.constants.PriorityType;
import com.smefinance.todo.constants.TaskStatus;
import com.smefinance.todo.entities.TodoTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        return this.getTodoTask(TaskStatus.TODO);
    }

    @Test
    void test_save() {
        TodoTask todoTask = this.getTodoTask();
        TodoTask savedData = entityManager.persist(todoTask);

        Optional<TodoTask> foundDataOptional = repository.findById(savedData.getId());

        assertThat(foundDataOptional).isPresent().get().isEqualTo(savedData);
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
