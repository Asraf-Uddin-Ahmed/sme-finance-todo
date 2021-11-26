package com.smefinance.todo.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.smefinance.todo.contants.PriorityType;
import com.smefinance.todo.contants.TaskStatus;

import lombok.Data;

@Entity
@Table(name = "todo_task")
@Data
public class TodoTask {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "title", nullable = false, length = 1000)
	private String title;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	@Column(name = "priority", nullable = false, columnDefinition="ENUM('LOW', 'MEDIUM', 'HIGH')")
	@Enumerated(EnumType.STRING)
	private PriorityType priority;
	
	@Column(name = "status", nullable = false, columnDefinition="ENUM('TODO', 'DOING', 'DONE')")
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at", nullable = false, length = 19)
	private Date modifiedAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		modifiedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		modifiedAt = new Date();
	}
}
