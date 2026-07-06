package com.example.todolist.service;

import com.example.todolist.dto.TaskRequest;
import com.example.todolist.dto.TaskResponse;
import com.example.todolist.entity.Task;
import com.example.todolist.entity.TaskStatus;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponse> getAll() {
        return taskRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse getById(Long id) {
        return toResponse(findEntityById(id));
    }

    public TaskResponse create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse update(Long id, TaskRequest request) {
        Task task = findEntityById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse toggleStatus(Long id) {
        Task task = findEntityById(id);
        task.setStatus(task.getStatus() == TaskStatus.TODO ? TaskStatus.DONE : TaskStatus.TODO);
        return toResponse(taskRepository.save(task));
    }

    public void delete(Long id) {
        taskRepository.delete(findEntityById(id));
    }

    private Task findEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy task với id: " + id));
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        BeanUtils.copyProperties(task, response);
        return response;
    }
}