package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "Title không được để trống")
    @Size(max = 255, message = "Title tối đa 255 ký tự")
    private String title;

    @Size(max = 1000, message = "Description tối đa 1000 ký tự")
    private String description;
}