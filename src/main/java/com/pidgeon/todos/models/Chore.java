package com.pidgeon.todos.models;

import javax.persistence.*;

@Entity
@Table(name = "chores")
public class Chore {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name="todo_id")
  @JoinColumn(columnDefinition = "todo_id")
  @JoinTable(name = "todos")
  private long todoId;

  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTodoId() {
    return todoId;
  }

  public void setTodoId(long todoId) {
    this.todoId = todoId;
  }
}
