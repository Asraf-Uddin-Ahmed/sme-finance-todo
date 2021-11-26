package com.smefinance.todo.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.smefinance.todo.contants.PriorityType;

import lombok.Data;

@Data
public class TodoTaskRequest {

	@NotBlank
	@Size(max = 1000)
	private String title;
	
	private String description;
	
	@NotNull
	private PriorityType priority;
	
}
