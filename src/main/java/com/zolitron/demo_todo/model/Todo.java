package com.zolitron.demo_todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String task;

    public Todo(){

    }

    public int getId() {
        return id;
    }
    public String getTask() {
        return task;
    }
    public void setId(int id){
     this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
