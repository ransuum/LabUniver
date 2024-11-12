package org.practice.service;

import org.practice.entity.Task;
import org.practice.entity.req.TaskUpdate;
import org.practice.service.serviceManagement.interfaces.CrudOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class TaskService implements CrudOperation {
    protected final List<Task> tasks = new ArrayList<>();

    @Override
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Task updateTask(UUID id, TaskUpdate task) {
        Task task1 = getTask(id);
        if (task.getTitle() != null) task1.setTitle(task.getTitle());
        if (task.getDescription() != null) task1.setDescription(task.getDescription());
        if (task.getCount() > 0) task1.setCount(task.getCount());
        if (task.getDueDate() != null) task1.setDueDate(task.getDueDate());
        return task1;
    }

    @Override
    public UUID removeTask(UUID id) {
        try {
            int indexToRemove = -1;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId().equals(id)) {
                    indexToRemove = i;
                    break;
                }
            }

            if (indexToRemove != -1) {
                tasks.remove(indexToRemove);
                return id;
            } else throw new IllegalArgumentException("Task with id " + id + " does not exist");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Task getTask(UUID id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Task with id " + id + " doesn't exist"));
    }

    public abstract void displayTasks();

    public abstract void sortByDueDate();

    public abstract void sortByPriority();

    public abstract void sortByPriorityAndDueDate();

    public abstract Task markTaskCompleted(UUID id);
    public abstract Task startTask(UUID id);
}
