package com.smefinance.todo.dtos.mapper.impl;

import com.smefinance.todo.dtos.mapper.TodoTaskMapper;
import com.smefinance.todo.dtos.request.TodoTaskRequest;
import com.smefinance.todo.dtos.response.TodoTaskResponse;
import com.smefinance.todo.entities.TodoTask;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoTaskMapperImpl implements TodoTaskMapper {

    private final ModelMapper modelMapperStrict;

    @Autowired
    public TodoTaskMapperImpl(ModelMapper modelMapperStrict) {
        this.modelMapperStrict = modelMapperStrict;
    }

    @Override
    public TodoTask getEntity(final TodoTaskRequest request) {
        return this.modelMapperStrict.map(request, TodoTask.class);
    }

    @Override
    public void loadEntity(final TodoTaskRequest request, final TodoTask entity) {
        this.modelMapperStrict.map(request, entity);
    }

    @Override
    public TodoTaskResponse getResponse(final TodoTask entity) {
        return this.modelMapperStrict.map(entity, TodoTaskResponse.class);
    }

    @Override
    public List<TodoTaskResponse> getResponses(final List<TodoTask> entities) {
        return entities.stream().map(this::getResponse).collect(Collectors.toList());
    }
}
