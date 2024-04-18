package ca.glotov.restexample.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Todo {
    private String id;
    private String description;
    private String completionStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id.equals(todo.id) &&
                description.equals(todo.description) &&
                completionStatus.equals(todo.completionStatus);
    }
}
