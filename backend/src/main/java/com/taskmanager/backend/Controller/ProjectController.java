package com.taskmanager.backend.Controller;

import com.taskmanager.backend.Entity.Project;
import com.taskmanager.backend.Repository.ProjectRepository;
import com.taskmanager.backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin

public class ProjectController {

    private final ProjectRepository projectRepository;
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping
    public List<Project> getAll() {
        return projectRepository.findAll();
    }
}