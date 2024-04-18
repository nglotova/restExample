package ca.glotov.restexample.todolist.dao;

import ca.glotov.restexample.todolist.model.Todo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo createTodo(Todo todo) {
        String sql = "INSERT INTO todoList (id, description, completion_status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, todo.getId(), todo.getDescription(), todo.getCompletionStatus());
        return todo;
    }

    public Todo getTodoById(String id) {
        String sql = "SELECT * FROM todoList WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TodoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Todo> getAllTodos() {
        String sql = "SELECT * FROM todoList";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

    public Todo updateTodo(Todo todo) {
        String sql = "UPDATE todoList SET description = ?, completion_status = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, todo.getDescription(), todo.getCompletionStatus(), todo.getId());
        } catch (Exception e) {
            return null;
        }
        return getTodoById(todo.getId());
    }

    public int deleteTodo(String id) {
        String sql = "DELETE FROM todoList WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private static class TodoRowMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Todo(rs.getString("id"), rs.getString("description"), rs.getString("completion_status"));
        }
    }
}