package com.pidgeon.todos.controllers;

import com.pidgeon.todos.models.Todo;
import com.pidgeon.todos.repositories.TodoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodosController {
  @Resource
  private TodoRepository repository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Todo> listTodos() {
    List<Todo> todos = new ArrayList<>();
    repository.findAll().forEach(todos::add);

    return todos;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Optional<Todo> get(@PathVariable long id) {
    return repository.findById(id);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public Todo create(@RequestBody Todo todo) {
    repository.save(todo);

    return todo;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Todo update(@PathVariable long id, @RequestBody Todo todo) {
    if (todo.getId() == 0) {
      todo.setId(id);
    } else if (id != todo.getId()) {
      repository.deleteById(id);
    }
    repository.save(todo);

    return todo;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable long id) {
    repository.deleteById(id);
  }

  @PostConstruct
  public void init() {
    insertTodo("Learn Spring Boot");
    insertTodo("Learn JPA");
    insertTodo("Learn Maven");
  }

  private void insertTodo(String task) {
    Todo todo = new Todo();

    todo.setTask(task);
    repository.save(todo);
  }
}
