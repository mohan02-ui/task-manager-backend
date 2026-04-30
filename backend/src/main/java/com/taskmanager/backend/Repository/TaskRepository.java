package com.taskmanager.backend.Repository;

import com.taskmanager.backend.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
