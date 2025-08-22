package com.todoapp.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.backend.dto.TodoDto;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    private TodoDto.Response todoResponse;
    private TodoDto.CreateRequest createRequest;

    @BeforeEach
    void setUp() {
        // 테스트용 할 일 데이터 준비
        todoResponse = new TodoDto.Response();
        todoResponse.setId(1L);
        todoResponse.setTitle("테스트 할 일");
        todoResponse.setDescription("테스트 설명");
        todoResponse.setPriority("HIGH");
        todoResponse.setCompleted(false);
        todoResponse.setCreatedAt(LocalDateTime.now());
        todoResponse.setUpdatedAt(LocalDateTime.now());

        // 테스트용 생성 요청 데이터 준비
        createRequest = new TodoDto.CreateRequest();
        createRequest.setTitle("새로운 할 일");
        createRequest.setDescription("새로운 설명");
        createRequest.setPriority("MEDIUM");
    }

    @Test
    void getAllTodos_성공() throws Exception {
        // Given
        List<TodoDto.Response> todos = Arrays.asList(todoResponse);
        when(todoService.getAllTodos()).thenReturn(todos);

        // When & Then
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("테스트 할 일"));
    }

    @Test
    void createTodo_성공() throws Exception {
        // Given
        TodoDto.Response createdTodo = new TodoDto.Response();
        createdTodo.setId(2L);
        createdTodo.setTitle("새로운 할 일");
        createdTodo.setPriority("MEDIUM");
        createdTodo.setCompleted(false);

        when(todoService.createTodo(any(), any(), any())).thenReturn(createdTodo);

        // When & Then
        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.title").value("새로운 할 일"));
    }

    @Test
    void updateTodo_성공() throws Exception {
        // Given
        TodoDto.UpdateRequest updateRequest = new TodoDto.UpdateRequest();
        updateRequest.setTitle("수정된 제목");

        TodoDto.Response updatedTodo = new TodoDto.Response();
        updatedTodo.setId(1L);
        updatedTodo.setTitle("수정된 제목");

        when(todoService.updateTodo(eq(1L), any(), any(), any(), any())).thenReturn(updatedTodo);

        // When & Then
        mockMvc.perform(put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("수정된 제목"));
    }

    @Test
    void toggleComplete_성공() throws Exception {
        // Given
        TodoDto.Response toggledTodo = new TodoDto.Response();
        toggledTodo.setId(1L);
        toggledTodo.setCompleted(true);

        when(todoService.toggleComplete(1L)).thenReturn(toggledTodo);

        // When & Then
        mockMvc.perform(patch("/api/todos/1/toggle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void deleteTodo_성공() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getStats_성공() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/todos/stats"))
                .andExpect(status().isOk());
    }
}
