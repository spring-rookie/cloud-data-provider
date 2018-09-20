package com.pidgeon.todos.repositories;

import com.pidgeon.todos.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
