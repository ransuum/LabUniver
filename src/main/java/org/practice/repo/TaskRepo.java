package org.practice.repo;

import org.practice.entity.Task;

import java.util.List;

public interface TaskRepo {
    List<Task> getTasks();
    void addTask(Task task);
    void removeTask(Task task);
}
