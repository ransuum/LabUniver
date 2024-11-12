package org.practice.service;

import org.practice.action.Action;
import org.practice.entity.Task;
import org.practice.entity.req.TaskUpdate;
import org.practice.repo.TaskRepo;
import org.practice.service.serviceManagement.interfaces.CrudOperation;

import java.util.*;

public class TaskOperationImpl implements CrudOperation {
    private final TaskRepo repository;

    public TaskOperationImpl(TaskRepo repository) {
        this.repository = repository;
    }

    @Override
    public Task addTask(Task task) {
        repository.addTask(task);
        return task;
    }

    @Override
    public Task updateTask(UUID id, TaskUpdate update) {
        Task task = getTask(id);
        if (update.getTitle() != null) task.setTitle(update.getTitle());
        if (update.getDescription() != null) task.setDescription(update.getDescription());
        if (update.getCount() > 0) task.setCount(update.getCount());
        if (update.getDueDate() != null) task.setDueDate(update.getDueDate());
        if (update.getPriority() != 0) task.setPriority(update.getPriority());
        return task;
    }

    @Override
    public UUID removeTask(UUID id) {
        Task task = getTask(id);
        repository.removeTask(task);
        return id;
    }

    @Override
    public Task getTask(UUID id) {
        return repository.getTasks().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " doesn't exist"));
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.getTasks();
    }

    @Override
    public Task markTaskCompleted(UUID id) {
        Task task = getTask(id);
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
        repository.getTasks().forEach(System.out::println);
    }

    @Override
    public void sortByDueDate() {
        repository.getTasks().stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .forEach(System.out::println);
    }

    @Override
    public void sortByPriority() {
        repository.getTasks().stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .forEach(System.out::println);
    }

    @Override
    public void sortByPriorityAndDueDate() {
        repository.getTasks().stream()
                .sorted(Comparator.comparing(Task::getPriority)
                        .thenComparing(Task::getDueDate))
                .forEach(System.out::println);
    }
}
