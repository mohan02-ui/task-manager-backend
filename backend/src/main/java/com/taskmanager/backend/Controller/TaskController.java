package com.taskmanager.backend.Controller;

import com.taskmanager.backend.Entity.Task;
import com.taskmanager.backend.Entity.User;
import com.taskmanager.backend.Repository.TaskRepository;
import com.taskmanager.backend.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // ================= GET TASKS (with optional filter) =================
    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) Long userId) {

        List<Task> tasks = taskRepository.findAll();

        if (userId != null) {
            return tasks.stream()
                    .filter(t -> t.getAssignedTo() != null
                            && t.getAssignedTo().getId().equals(userId))
                    .toList();
        }

        return tasks;
    }

    // ================= CREATE TASK =================
    @PostMapping
    public Task create(@RequestBody Task task) {

        task.setStatus("TODO");

        // 🔥 FIX: Properly attach User entity from DB
        if (task.getAssignedTo() != null && task.getAssignedTo().getId() != null) {
            Long userId = task.getAssignedTo().getId();

            User user = userRepository.findById(userId).orElse(null);
            task.setAssignedTo(user);
        }

        return taskRepository.save(task);
    }

    // ================= UPDATE STATUS =================
    @PutMapping("/{id}")
    public Task updateStatus(@PathVariable Long id, @RequestBody Task updated) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(updated.getStatus());

        return taskRepository.save(task);
    }
}