package ca.glotov.restexample;

import ca.glotov.restexample.todolist.dao.TodoRepository;
import ca.glotov.restexample.todolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ca.glotov.restexample.RestExampleApplication.class)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testCreateTodo() {
        String uniqueId = UUID.randomUUID().toString();
        Todo todo = new Todo(uniqueId, "Test Todo", "Incomplete");
        todo = todoRepository.createTodo(todo);

        assertNotNull(todo.getId(), "The saved todo should have an id");
        assertEquals("Test Todo", todo.getDescription(), "The saved todo description should match the original");
    }

    @Test
    public void testFindTodo() {
        String uniqueId = UUID.randomUUID().toString();
        Todo todo = new Todo(uniqueId, "Test Todo", "Incomplete");
        todo = todoRepository.createTodo(todo);

        Todo foundTodo = todoRepository.getTodoById(todo.getId());

        assertNotNull(foundTodo, "Todo should be found");
        assertEquals(todo.getId(), foundTodo.getId(), "Found todo id should match the original");
        assertEquals(todo.getDescription(), foundTodo.getDescription(), "Found todo description should match the original");
    }

    @Test
    public void testUpdateTodo() {
        String uniqueId = UUID.randomUUID().toString();
        Todo todo = new Todo(uniqueId, "Test Todo", "Incomplete");
        todo = todoRepository.createTodo(todo);

        todo.setDescription("Updated Todo");
        todo.setCompletionStatus("Complete");
        todo = todoRepository.updateTodo(todo);

        Todo updatedTodo = todoRepository.getTodoById(todo.getId());

        assertNotNull(updatedTodo, "Updated todo should not be null");
        assertEquals("Updated Todo", updatedTodo.getDescription(), "Updated todo description should match the new description");
        assertEquals("Complete", updatedTodo.getCompletionStatus(), "Updated todo completion status should match the new status");
    }

    @Test
    public void testDeleteTodo() {
        String uniqueId = UUID.randomUUID().toString();
        Todo todo = new Todo(uniqueId, "Test Todo", "Incomplete");
        todo = todoRepository.createTodo(todo);

        int rowsAffected = todoRepository.deleteTodo(todo.getId());

        Todo deletedTodo = todoRepository.getTodoById(todo.getId());

        assertEquals(1, rowsAffected, "One row should be affected when deleting a todo");
        assertNull(deletedTodo, "Deleted todo should be null when retrieved from the database");
    }
}
