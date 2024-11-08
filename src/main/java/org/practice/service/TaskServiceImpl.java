package org.practice.service;

import org.practice.action.Action;
import org.practice.entity.Task;

import java.util.*;

public class TaskServiceImpl extends TaskService {

    @Override
    public Task markTaskCompleted(UUID id) {
        Task task = getTasks().stream()
                .filter(task1 -> task1.getId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new RuntimeException("Task with id " + id + " doesn't exist"));
        task.setCompleted(true);

        return task;
    }

    @Override
    public Task startTask(UUID id) {
        Task task = getTask(id);
        if (task.getCount() <= 0)
            throw new RuntimeException("Task with id " + id + " doesn't have count on some tasks");

        Action action = new Action(task);
        action.start();

        try {
            action.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.setCompleted(true);
        return task;
    }

    @Override
    public void displayTasks() {
        getTasks().forEach(System.out::println);
    }

    @Override
    public void sortByDueDate() {
        getTasks().stream().sorted(Comparator.comparing(Task::getDueDate))
                .toList().forEach(System.out::println);
    }

    @Override
    public void sortByPriority() {
        getTasks().stream().sorted(Comparator.comparing(Task::getPriority))
                .toList().forEach(System.out::println);
    }

    @Override
    public void sortByPriorityAndDueDate() {
        getTasks().stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList().forEach(System.out::println);
    }
}
