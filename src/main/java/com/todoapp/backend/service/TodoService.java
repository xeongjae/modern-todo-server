package com.todoapp.backend.service;

import com.todoapp.backend.dto.TodoDto;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDto.Response> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(TodoDto.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public TodoDto.Response createTodo(String title, String description, String priority) {
        Todo todo = new Todo(title, description, Todo.Priority.valueOf(priority), false);
        Todo savedTodo = todoRepository.save(todo);
        return TodoDto.Response.fromEntity(savedTodo);
    }

    public TodoDto.Response updateTodo(Long id, String title, String description, String priority, Boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다: " + id));

        if (title != null) todo.setTitle(title);
        if (description != null) todo.setDescription(description);
        if (priority != null) todo.setPriority(Todo.Priority.valueOf(priority));
        if (completed != null) todo.setCompleted(completed);

        Todo updatedTodo = todoRepository.save(todo);
        return TodoDto.Response.fromEntity(updatedTodo);
    }

    public TodoDto.Response toggleComplete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다: " + id));

        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return TodoDto.Response.fromEntity(updatedTodo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("할 일을 찾을 수 없습니다: " + id);
        }
        todoRepository.deleteById(id);
    }

    public java.util.Map<Todo.Priority, Long> getTodoCountByPriority() {
        java.util.Map<Todo.Priority, Long> counts = new java.util.HashMap<>();
        counts.put(Todo.Priority.HIGH, todoRepository.countByPriority(Todo.Priority.HIGH));
        counts.put(Todo.Priority.MEDIUM, todoRepository.countByPriority(Todo.Priority.MEDIUM));
        counts.put(Todo.Priority.LOW, todoRepository.countByPriority(Todo.Priority.LOW));
        return counts;
    }
}