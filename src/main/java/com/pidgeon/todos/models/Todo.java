package com.pidgeon.todos.models;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String task;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }
}
