package com.todoapp.backend.dto;

import com.todoapp.backend.entity.Todo;
import java.time.LocalDateTime;

public class TodoDto {

    public static class Response {
        private Long id;
        private String title;
        private String description;
        private String priority;
        private Boolean completed;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Response() {}

        public Response(Long id, String title, String description, String priority, Boolean completed, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.priority = priority;
            this.completed = completed;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        // Getters
        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getPriority() { return priority; }
        public Boolean getCompleted() { return completed; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }

        // Setters
        public void setId(Long id) { this.id = id; }
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
        public void setPriority(String priority) { this.priority = priority; }
        public void setCompleted(Boolean completed) { this.completed = completed; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

        public static Response fromEntity(Todo todo) {
            Response dto = new Response();
            dto.setId(todo.getId());
            dto.setTitle(todo.getTitle());
            dto.setDescription(todo.getDescription());
            dto.setPriority(todo.getPriority().name());
            dto.setCompleted(todo.getCompleted());
            dto.setCreatedAt(todo.getCreatedAt());
            dto.setUpdatedAt(todo.getUpdatedAt());
            return dto;
        }
    }

    public static class CreateRequest {
        private String title;
        private String description;
        private String priority;

        public CreateRequest() {}

        public CreateRequest(String title, String description, String priority) {
            this.title = title;
            this.description = description;
            this.priority = priority;
        }

        // Getters
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getPriority() { return priority; }

        // Setters
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
        public void setPriority(String priority) { this.priority = priority; }
    }

    public static class UpdateRequest {
        private String title;
        private String description;
        private String priority;
        private Boolean completed;

        public UpdateRequest() {}

        public UpdateRequest(String title, String description, String priority, Boolean completed) {
            this.title = title;
            this.description = description;
            this.priority = priority;
            this.completed = completed;
        }

        // Getters
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getPriority() { return priority; }
        public Boolean getCompleted() { return completed; }

        // Setters
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
        public void setPriority(String priority) { this.priority = priority; }
        public void setCompleted(Boolean completed) { this.completed = completed; }
    }
}

