package com.smefinance.todo.services;

import com.smefinance.todo.entities.TodoTask;
import com.smefinance.todo.exception.ResourceNotFoundException;
import com.smefinance.todo.repositories.TodoTaskRepository;
import com.smefinance.todo.services.imp.TodoTaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoTaskServiceTest {

    @InjectMocks
    private TodoTaskServiceImpl todoTaskService;

    @Mock
    private TodoTaskRepository repository;

    @Test
    void whenSaveTodoTask_thenTaskShouldBeFound() {
        TodoTask givenTodoTask = new TodoTask();
        when(repository.save(any())).thenReturn(givenTodoTask);

        TodoTask foundTodoTask = todoTaskService.save(new TodoTask());

        assertThat(foundTodoTask).isEqualTo(givenTodoTask);
    }

    @Test
    void whenSaveWithTaskStatus_thenTaskShouldBeFound() {
        TodoTask givenTodoTask = new TodoTask();
        when(repository.save(any())).thenReturn(givenTodoTask);

        TodoTask foundTodoTask = todoTaskService.saveWithTaskStatus(new TodoTask(), null);

        assertThat(foundTodoTask).isEqualTo(givenTodoTask);
    }

    @Test
    void whenDeleteTheTask_thenTaskShouldBeMarkAsDeleted() {
        TodoTask givenTodoTask = new TodoTask();
        when(repository.save(any())).thenReturn(givenTodoTask);

        todoTaskService.delete(givenTodoTask);

        assertThat(givenTodoTask.isDeleted()).isTrue();
    }

    @Test
    void whenGetAllByTaskStatus_thenTaskShouldBeFound() {
        TodoTask givenTodoTask = new TodoTask();
        when(repository.findAllBy(any())).thenReturn(Collections.singletonList(givenTodoTask));

        List<TodoTask> foundTodoTasks = todoTaskService.getAllBy(null);

        assertThat(foundTodoTasks.get(0)).isEqualTo(givenTodoTask);
    }

    @Test
    void whenGetByIdTaskStatus_thenTaskShouldBeFound() {
        TodoTask givenTodoTask = new TodoTask();
        givenTodoTask.setId(0);
        when(repository.findByIdAndIsDeletedFalse(givenTodoTask.getId())).thenReturn(givenTodoTask);

        TodoTask foundTodoTask = todoTaskService.getById(givenTodoTask.getId());

        assertThat(foundTodoTask).isEqualTo(givenTodoTask);
    }

    @Test
    void whenGetByIdNotFound_thenThrowException() {
        TodoTask givenTodoTask = new TodoTask();
        givenTodoTask.setId(0);
        when(repository.findByIdAndIsDeletedFalse(givenTodoTask.getId())).thenReturn(null);

        assertThatThrownBy(() -> todoTaskService.getById(givenTodoTask.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

}
