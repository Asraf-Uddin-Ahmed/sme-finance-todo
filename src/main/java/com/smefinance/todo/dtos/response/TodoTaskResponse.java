package com.smefinance.todo.dtos.response;

import com.smefinance.todo.contants.PriorityType;
import com.smefinance.todo.contants.TaskStatus;

import lombok.Data;

@Data
public class TodoTaskResponse {
	private Integer id;
	private String title;
	private String description;
	private PriorityType priority;
	private TaskStatus status;
}
