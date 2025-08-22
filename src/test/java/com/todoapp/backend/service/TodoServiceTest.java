package com.todoapp.backend.service;

import com.todoapp.backend.dto.TodoDto;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo testTodo;

    @BeforeEach
    void setUp() {
        // 테스트용 할 일 데이터 준비
        testTodo = new Todo();
        testTodo.setId(1L);
        testTodo.setTitle("테스트 할 일");
        testTodo.setDescription("테스트 설명");
        testTodo.setPriority(Todo.Priority.HIGH);
        testTodo.setCompleted(false);
    }

    @Test
    void getAllTodos_성공() {
        // Given
        List<Todo> todos = Arrays.asList(testTodo);
        when(todoRepository.findAll()).thenReturn(todos);

        // When
        List<TodoDto.Response> result = todoService.getAllTodos();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("테스트 할 일", result.get(0).getTitle());
    }

    @Test
    void createTodo_성공() {
        // Given
        Todo newTodo = new Todo();
        newTodo.setId(2L);
        newTodo.setTitle("새로운 할 일");
        newTodo.setDescription("새로운 설명");
        newTodo.setPriority(Todo.Priority.MEDIUM);
        newTodo.setCompleted(false);

        when(todoRepository.save(any(Todo.class))).thenReturn(newTodo);

        // When
        TodoDto.Response result = todoService.createTodo("새로운 할 일", "새로운 설명", "MEDIUM");

        // Then
        assertNotNull(result);
        assertEquals("새로운 할 일", result.getTitle());
        assertEquals("MEDIUM", result.getPriority());
    }

    @Test
    void updateTodo_성공() {
        // Given
        when(todoRepository.findById(1L)).thenReturn(Optional.of(testTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);

        // When
        TodoDto.Response result = todoService.updateTodo(1L, "수정된 제목", null, null, null);

        // Then
        assertNotNull(result);
        assertEquals("수정된 제목", result.getTitle());
    }

    @Test
    void updateTodo_존재하지않는_할일() {
        // Given
        when(todoRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            todoService.updateTodo(999L, "수정된 제목", null, null, null);
        });
    }

    @Test
    void toggleComplete_성공() {
        // Given
        when(todoRepository.findById(1L)).thenReturn(Optional.of(testTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);

        // When
        TodoDto.Response result = todoService.toggleComplete(1L);

        // Then
        assertNotNull(result);
        assertTrue(result.getCompleted());
    }

    @Test
    void deleteTodo_성공() {
        // Given
        when(todoRepository.existsById(1L)).thenReturn(true);

        // When & Then
        assertDoesNotThrow(() -> {
            todoService.deleteTodo(1L);
        });
    }

    @Test
    void deleteTodo_존재하지않는_할일() {
        // Given
        when(todoRepository.existsById(999L)).thenReturn(false);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            todoService.deleteTodo(999L);
        });
    }

    @Test
    void getTodoCountByPriority_성공() {
        // Given
        when(todoRepository.countByPriority(Todo.Priority.HIGH)).thenReturn(5L);
        when(todoRepository.countByPriority(Todo.Priority.MEDIUM)).thenReturn(10L);
        when(todoRepository.countByPriority(Todo.Priority.LOW)).thenReturn(3L);

        // When
        var result = todoService.getTodoCountByPriority();

        // Then
        assertNotNull(result);
        assertEquals(5L, result.get(Todo.Priority.HIGH));
        assertEquals(10L, result.get(Todo.Priority.MEDIUM));
        assertEquals(3L, result.get(Todo.Priority.LOW));
    }
}
