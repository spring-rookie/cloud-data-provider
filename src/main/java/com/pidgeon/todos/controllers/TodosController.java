package com.pidgeon.todos.controllers;

import com.pidgeon.todos.models.Todo;
import com.pidgeon.todos.repositories.TodoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodosController {
  @Resource
  private TodoRepository repository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Todo> listTodos() {
    List<Todo> todos = new ArrayList<>();
    repository.findAll().forEach(todos::add);

    return todos;
  }

  @Cacheable("todo")
  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET
  )
  public ResponseEntity<Todo> get(@PathVariable long id) {
    Optional<Todo> t = repository.findById(id);

    if (t.isPresent()) {
      System.out.println(t.get());

      return new ResponseEntity<>(t.get(), new HttpHeaders(), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public Todo create(@RequestBody Todo todo) {
    repository.save(todo);

    return todo;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Todo> update(@PathVariable long id, @RequestBody Todo todo) {
    Optional<Todo> t = repository.findById(id);

    if (t.isPresent()) {
      t.get().setTask(todo.getTask());

      Todo updatedTodo = repository.save(t.get());

      return new ResponseEntity<>(updatedTodo, new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
    Optional<Todo> t = repository.findById(id);

    fields.forEach((k, v) -> {
      Field field = ReflectionUtils.findField(Todo.class, k);
      field.setAccessible(true);

      switch(field.getType().getSimpleName()) {
        case "Chore":
          t.get().setChore((Chore)v);
          break;
        default:
          ReflectionUtils.setField(field, t.get(), v);
          break;
      }
    });

    repository.save(t.get());
    Optional<Todo> savedT = repository.findById(id);

    return new ResponseEntity<>(savedT.get(), new HttpHeaders(), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity delete(@PathVariable long id) {
    repository.deleteById(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
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
