package com.taskmanager.repository;

import com.taskmanager.entity.Project;
import com.taskmanager.entity.TaskItem;
import com.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskItem, Long> {
    List<TaskItem> findByProject(Project project);
    List<TaskItem> findByAssignedTo(User user);
}
