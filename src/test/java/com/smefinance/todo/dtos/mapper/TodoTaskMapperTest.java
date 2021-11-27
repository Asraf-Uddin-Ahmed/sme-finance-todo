package com.smefinance.todo.dtos.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.smefinance.todo.dtos.mapper.impl.TodoTaskMapperImpl;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;

@SpringBootTest
class TodoTaskMapperTest {

	@InjectMocks
    private TodoTaskMapperImpl todoTaskMapper;

	@Mock
	private ModelMapper modelMapperStrict;

	@Test
	public void whenProvideRequest_thenGetEntity() {
		TodoTask givenTodoTask = new TodoTask();
		TodoTaskRequest request = new TodoTaskRequest();
		when(modelMapperStrict.map(request, TodoTask.class)).thenReturn(givenTodoTask);
        
		TodoTask foundTodoTask = todoTaskMapper.getEntity(request);
		
		assertThat(foundTodoTask).isEqualTo(givenTodoTask);
	}
	
	@Test
	public void whenProvideRequestAndEntity_thenLoadEntity() {
		TodoTask givenTodoTask = new TodoTask();
		TodoTaskRequest givenRequest = new TodoTaskRequest();
		doNothing().when(modelMapperStrict).map(givenRequest, givenTodoTask);

		todoTaskMapper.loadEntity(givenRequest, givenTodoTask);
		
		verify(modelMapperStrict, times(1)).map(givenRequest, givenTodoTask);
	}
	
	@Test
	public void whenProvideEntity_thenGetResponse() {
		TodoTask givenTodoTask = new TodoTask();
		TodoTaskResponse givenResponse = new TodoTaskResponse();
		when(modelMapperStrict.map(givenTodoTask, TodoTaskResponse.class)).thenReturn(givenResponse);
        
		TodoTaskResponse foundResponse = todoTaskMapper.getResponse(givenTodoTask);
		
		assertThat(foundResponse).isEqualTo(givenResponse);
	}
	
	@Test
	public void whenProvideEntityList_thenGetResponseList() {
		TodoTask givenTodoTask = new TodoTask();
		TodoTaskResponse givenResponse = new TodoTaskResponse();
		when(modelMapperStrict.map(givenTodoTask, TodoTaskResponse.class)).thenReturn(givenResponse);
        
		List<TodoTaskResponse> foundResponseList = todoTaskMapper.getResponses(Collections.singletonList(givenTodoTask));
		
		assertThat(foundResponseList.get(0)).isEqualTo(givenResponse);
	}
	
}
