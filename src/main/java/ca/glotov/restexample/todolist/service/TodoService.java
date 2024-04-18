package ca.glotov.restexample.todolist.service;

import ca.glotov.restexample.todolist.dao.TodoRepository;
import ca.glotov.restexample.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    public Todo getTodoById(String id) {
        return todoRepository.getTodoById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.createTodo(todo);
    }

    public Todo updateTodo(Todo todo) {
        return todoRepository.updateTodo(todo);
    }

    public void deleteTodo(String id) {
        todoRepository.deleteTodo(id);
    }
}