package lt.vtmc.projectTaskManagement.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vtmc.projectTaskManagement.enums.Priority;
import lt.vtmc.projectTaskManagement.enums.State;

@Entity
@Getter @Setter @NoArgsConstructor
public class TaskEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String taskTitle;
	private String taskDescription;
	@Enumerated(EnumType.STRING)
	private Priority taskPriority;
	@Enumerated(EnumType.STRING)
	private State taskState;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private ProjectEntity project;

	public TaskEntity(String taskTitle, String taskDescription, Priority taskPriority, State taskState,
			ProjectEntity project) {

		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskPriority = taskPriority;
		this.taskState = taskState;
		this.project = project;
		createdAt=new Date();
		updatedAt=new Date();
	}
	
	public TaskEntity(Task task, ProjectEntity project) {
		super();
		this.taskTitle = task.getTaskTitle();
		this.taskDescription = task.getTaskDescription();
		this.taskPriority = Priority.valueOf(task.getTaskPriority());
		this.taskState = State.valueOf(task.getTaskState());
		
		createdAt=new Date();
		updatedAt=new Date();

		this.project = project;
	}

	public void updateTask(Task task) {

		this.taskTitle = task.getTaskTitle();
		this.taskDescription = task.getTaskDescription();
		this.taskPriority = Priority.valueOf(task.getTaskPriority());
		this.taskState = State.valueOf(task.getTaskState());

		this.updatedAt = new Date();

	}



	
	
	
	
	
	

}
