package com.taskmanager.backend.Repository;

import com.taskmanager.backend.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
