package org.practice.request;

import org.practice.entity.Task;
import org.practice.entity.req.TaskUpdate;
import org.practice.service.TaskService;
import org.practice.service.serviceManagement.interfaces.CrudOperation;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class TaskServiceRequests implements Requests {

    private final Scanner scanner = new Scanner(System.in);
    private final CrudOperation crudOperation;

    public TaskServiceRequests(CrudOperation crudOperation) {
        this.crudOperation = crudOperation;
    }

    @Override
    public Task createRequest() {
        try {
            System.out.print("Please enter the title of the task: ");
            String title = scanner.nextLine();
            System.out.print("Please enter the description of the task: ");
            String description = scanner.nextLine();
            System.out.print("Please enter the priority of the task: ");
            byte priority = (byte) scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Please enter the due date of the task (yyyy-MM-dd): ");
            String dueDate = scanner.nextLine();
            System.out.print("Please enter the count of the task: ");
            int count = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            return crudOperation.addTask(new Task.builder()
                    .completed(false)
                    .title(title)
                    .description(description)
                    .priority(priority)
                    .dueDate(LocalDate.parse(dueDate))
                    .count(count)
                    .build());
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Task updateRequest() {
        System.out.print("Please enter the UUID of the task: ");
        String uuid = scanner.next();
        System.out.print("Please enter the title of the task: ");
        String title = scanner.next();
        System.out.print("Please enter the description of the task: ");
        String description = scanner.next();
        System.out.print("Please enter the priority of the task: ");
        byte priority = (byte) scanner.nextInt();
        System.out.print("Please enter the due date of the task: ");
        String dueDate = scanner.next();
        System.out.print("Please enter the count of the task: ");
        int count = scanner.nextInt();

        return this.crudOperation.updateTask(UUID.fromString(uuid),
                new TaskUpdate.builder()
                        .count(count)
                        .title(title)
                        .description(description)
                        .priority(priority)
                        .dueDate(LocalDate.parse(dueDate))
                        .build());

    }

    @Override
    public UUID deleteRequest() {
        System.out.print("Please enter the UUID of the task: ");
        String uuid = scanner.next();

        return this.crudOperation.removeTask(UUID.fromString(uuid));
    }

    @Override
    public Task markTaskAsCompletedRequest() {
        System.out.print("Please enter the UUID of the task: ");
        String uuid = scanner.next();

        return this.crudOperation.markTaskCompleted(UUID.fromString(uuid));
    }

    @Override
    public void showAll() {
        this.crudOperation.displayTasks();
    }

    @Override
    public Task startTask() {
        System.out.print("Enter id: ");
        String uuid = scanner.next();
        return this.crudOperation.startTask(UUID.fromString(uuid));
    }
}
