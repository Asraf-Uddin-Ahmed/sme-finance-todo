package com.smefinance.todo.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.smefinance.todo.contants.PriorityType;
import com.smefinance.todo.contants.TaskStatus;

import lombok.Data;

@Data
public class TodoTaskRequest {

	@NotBlank
	private String title;
	
	private String description;
	
	@NotNull
	private PriorityType priority;
	
}
