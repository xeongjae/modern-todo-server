package com.todoapp.backend.controller;

import com.todoapp.backend.dto.TodoDto;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo API", description = "할 일 관리를 위한 REST API")
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "false")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @Operation(summary = "할 일 목록 조회", description = "모든 할 일 목록을 조회합니다.")
    public ResponseEntity<List<TodoDto.Response>> getAllTodos() {
        List<TodoDto.Response> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    @Operation(summary = "할 일 생성", description = "새로운 할 일을 생성합니다.")
    public ResponseEntity<TodoDto.Response> createTodo(
            @Valid @RequestBody TodoDto.CreateRequest request) {
        TodoDto.Response todo = todoService.createTodo(request.getTitle(), request.getDescription(), request.getPriority());
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "할 일 수정", description = "기존 할 일을 수정합니다.")
    public ResponseEntity<TodoDto.Response> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoDto.UpdateRequest request) {
        TodoDto.Response todo = todoService.updateTodo(id, request.getTitle(), request.getDescription(), request.getPriority(), request.getCompleted());
        return ResponseEntity.ok(todo);
    }

    @PatchMapping("/{id}/toggle")
    @Operation(summary = "할 일 완료 상태 토글", description = "할 일의 완료 상태를 반전시킵니다.")
    public ResponseEntity<TodoDto.Response> toggleComplete(@PathVariable Long id) {
        TodoDto.Response todo = todoService.toggleComplete(id);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "할 일 삭제", description = "할 일을 삭제합니다.")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    @Operation(summary = "할 일 통계 조회", description = "우선순위별 할 일 개수를 조회합니다.")
    public ResponseEntity<Map<Todo.Priority, Long>> getStats() {
        Map<Todo.Priority, Long> stats = todoService.getTodoCountByPriority();
        return ResponseEntity.ok(stats);
    }
}