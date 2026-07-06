package com.example.todolist.dto;

import com.example.todolist.entity.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}