package org.practice.controller;

import org.practice.repo.TaskRepo;
import org.practice.repo.TaskRepositoryImpl;
import org.practice.request.Requests;
import org.practice.request.TaskServiceRequests;
import org.practice.service.TaskOperationImpl;
import org.practice.service.serviceManagement.interfaces.CrudOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TaskController {
    private final Scanner scanner = new Scanner(System.in);
    private final CrudOperation crudOperation;
    private final Requests requests;

    public TaskController() {
        TaskRepo taskRepo = new TaskRepositoryImpl();
        this.crudOperation = new TaskOperationImpl(taskRepo);
        this.requests = new TaskServiceRequests(crudOperation);
    }

    public void start() throws IOException {
        boolean done = false;
        while (!done) {
            getText();
            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    System.out.println("Goodbye!");
                    done = true;
                    break;
                case "1":
                    System.out.println(requests.createRequest());
                    break;
                case "2":
                    requests.showAll();
                    break;
                case "3":
                    System.out.println(requests.deleteRequest());
                    break;
                case "4":
                    System.out.println(requests.updateRequest());
                    break;
                case "5":
                    System.out.println(requests.markTaskAsCompletedRequest());
                    break;
                case "6":
                    crudOperation.sortByDueDate();
                    break;
                case "7":
                    crudOperation.sortByPriority();
                    break;
                case "8":
                    crudOperation.sortByPriorityAndDueDate();
                    break;
                case "9":
                    System.out.println(requests.startTask());
                    break;
                default:
                    System.err.println("Invalid option");
                    break;
            }
        }
        scanner.close();
    }

    public void getText() throws IOException {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/practice/util/menu.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}
