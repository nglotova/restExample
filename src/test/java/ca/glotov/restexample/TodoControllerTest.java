package ca.glotov.restexample;


import ca.glotov.restexample.todolist.model.Todo;
import ca.glotov.restexample.todolist.service.TodoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoControllerTest {

    @Autowired
    private TodoController todoController;

    private Todo newTodo() {
        String uniqueId = UUID.randomUUID().toString();
        return new Todo(uniqueId, "Test todo", "Incomplete");
    }

    @Test
    public void createTodo() {
        Todo todo = newTodo();
        ResponseEntity<Todo> response = todoController.createTodo(todo);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(todo, response.getBody());
    }

    @Test
    public void getAllTodos() {
        ResponseEntity<List<Todo>> response = todoController.getAllTodos();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getTodoByIdSuccess() {
        Todo todo = newTodo();
        ResponseEntity<Todo> newTodo = todoController.createTodo(todo);
        ResponseEntity<?> response = todoController.getTodoById(newTodo.getBody().getId());
        assertNotNull(response);
        assertTrue(response.getBody() instanceof Todo, "Response body is not an instance of Todo");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getTodoByIdFail() {
        String id = UUID.randomUUID().toString();
        ResponseEntity<?> response = todoController.getTodoById(id);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void updateTodo() {
        Todo todo = newTodo();
        ResponseEntity<Todo> createdTodoResponse = todoController.createTodo(todo);

        // Modify the created todo
        Todo updatedTodo = createdTodoResponse.getBody();
        updatedTodo.setDescription("Updated description");
        updatedTodo.setCompletionStatus("Complete");

        // Call the updateTodo method
        ResponseEntity<Todo> response = todoController.updateTodo(updatedTodo.getId(), updatedTodo);

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedTodo, response.getBody());
    }

    @Test
    public void deleteTodo() {
        Todo todo = newTodo();
        ResponseEntity<Todo> newTodo = todoController.createTodo(todo);
        ResponseEntity<Void> response = todoController.deleteTodo(newTodo.getBody().getId());
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
    }

}


