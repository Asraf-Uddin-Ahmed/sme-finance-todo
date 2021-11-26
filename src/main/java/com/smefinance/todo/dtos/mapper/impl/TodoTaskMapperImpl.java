package com.smefinance.todo.dtos.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smefinance.todo.dtos.mapper.TodoTaskMapper;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.entities.TodoTask;

@Component
public class TodoTaskMapperImpl implements TodoTaskMapper {
	
	private ModelMapper modelMapperStrict;
	
	@Autowired
	public TodoTaskMapperImpl(ModelMapper modelMapperStrict) {
		this.modelMapperStrict = modelMapperStrict;
	}
	
	@Override
	public TodoTask getEntity(final TodoTaskRequest request) {
		return this.modelMapperStrict.map(request, TodoTask.class);
	}
}
