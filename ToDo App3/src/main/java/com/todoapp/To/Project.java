package com.todoapp.To;


import java.sql.Timestamp;  
import java.util.List;

public class Project {
    private int id;
    private String title;
    private Timestamp createdDate;
    private int userId;
    private List<Todo> todos;
	
    // Getters and Setters
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Todo> getTodos() {
		return todos;
	}
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

   
    
    
    
    
}
