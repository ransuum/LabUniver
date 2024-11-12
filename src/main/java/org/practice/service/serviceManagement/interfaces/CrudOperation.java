package org.practice.service.serviceManagement.interfaces;

import org.practice.entity.Task;
import org.practice.entity.req.TaskUpdate;

import java.util.List;
import java.util.UUID;

public interface CrudOperation {
    Task addTask(Task task);
    Task updateTask(UUID id, TaskUpdate task);
    UUID removeTask(UUID id);
    Task getTask(UUID id);
    List<Task> getAllTasks();
    Task markTaskCompleted(UUID id);
    Task startTask(UUID id);
    void displayTasks();
    void sortByDueDate();
    void sortByPriority();
    void sortByPriorityAndDueDate();
}
