package ca.glotov.restexample;

import ca.glotov.restexample.todolist.dao.TodoRepository;
import ca.glotov.restexample.todolist.model.Todo;
import ca.glotov.restexample.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo newTodo() {
        String uniqueId = UUID.randomUUID().toString();
        return new Todo(uniqueId, "Test todo", "Incomplete");
    }

    @Test
    public void testGetAllTodos() {
        todoService.getAllTodos();
        verify(todoRepository, times(1)).getAllTodos();
    }

    @Test
    public void testGetTodoById() {
        Todo todo = newTodo();
        todoService.createTodo(todo);
        todoService.getTodoById(todo.getId());
        verify(todoRepository, times(1)).getTodoById(todo.getId());
    }

    @Test
    public void testCreateTodo() {
        Todo todo = newTodo();
        todoService.createTodo(todo);
        verify(todoRepository, times(1)).createTodo(todo);
    }

    @Test
    public void testUpdateTodo() {
        Todo todoToUpdate = newTodo();
        todoService.createTodo(todoToUpdate);

        Todo updatedTodo = newTodo();
        updatedTodo.setId(todoToUpdate.getId()); // set the id of the updatedTodo to the id of the todoToUpdate
        todoService.updateTodo(updatedTodo);

        verify(todoRepository, times(1)).updateTodo(updatedTodo);
    }

    @Test
    public void testDeleteTodo() {
        Todo todo = newTodo();
        todoService.deleteTodo(todo.getId());
        verify(todoRepository, times(1)).deleteTodo(todo.getId());
    }
}