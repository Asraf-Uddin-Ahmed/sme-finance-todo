package com.smefinance.todo.dtos.mapper;

import com.smefinance.todo.dtos.mapper.impl.TodoTaskMapperImpl;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoTaskMapperTest {

    @InjectMocks
    private TodoTaskMapperImpl todoTaskMapper;

    @Mock
    private ModelMapper modelMapperStrict;

    @Test
    void whenProvideRequest_thenGetEntity() {
        TodoTask givenTodoTask = new TodoTask();
        TodoTaskRequest request = new TodoTaskRequest();
        when(modelMapperStrict.map(request, TodoTask.class)).thenReturn(givenTodoTask);

        TodoTask foundTodoTask = todoTaskMapper.getEntity(request);

        assertThat(foundTodoTask).isEqualTo(givenTodoTask);
    }

    @Test
    void whenProvideRequestAndEntity_thenLoadEntity() {
        TodoTask givenTodoTask = new TodoTask();
        TodoTaskRequest givenRequest = new TodoTaskRequest();
        doNothing().when(modelMapperStrict).map(givenRequest, givenTodoTask);

        todoTaskMapper.loadEntity(givenRequest, givenTodoTask);

        verify(modelMapperStrict, times(1)).map(givenRequest, givenTodoTask);
    }

    @Test
    void whenProvideEntity_thenGetResponse() {
        TodoTask givenTodoTask = new TodoTask();
        TodoTaskResponse givenResponse = new TodoTaskResponse();
        when(modelMapperStrict.map(givenTodoTask, TodoTaskResponse.class)).thenReturn(givenResponse);

        TodoTaskResponse foundResponse = todoTaskMapper.getResponse(givenTodoTask);

        assertThat(foundResponse).isEqualTo(givenResponse);
    }

    @Test
    void whenProvideEntityList_thenGetResponseList() {
        TodoTask givenTodoTask = new TodoTask();
        TodoTaskResponse givenResponse = new TodoTaskResponse();
        when(modelMapperStrict.map(givenTodoTask, TodoTaskResponse.class)).thenReturn(givenResponse);

        List<TodoTaskResponse> foundResponseList = todoTaskMapper.getResponses(Collections.singletonList(givenTodoTask));

        assertThat(foundResponseList.get(0)).isEqualTo(givenResponse);
    }

}
