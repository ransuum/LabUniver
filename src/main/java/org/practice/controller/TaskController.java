package org.practice.controller;

import org.practice.request.RequestImpl;
import org.practice.request.Requests;
import org.practice.service.TaskService;
import org.practice.service.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TaskController {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService = new TaskServiceImpl();
    private final Requests requests = new RequestImpl(taskService);

    public void start() throws IOException {
        boolean done = false;
        while (!done) {
            getText();
            String input = scanner.next();

            switch (input) {
                case "0":
                    System.out.println("bye");
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
                    taskService.sortByDueDate();
                    break;
                case "7":
                    taskService.sortByPriority();
                    break;
                case "8":
                    taskService.sortByPriorityAndDueDate();
                    break;
                case "9":
                    System.out.println(requests.startTask());
                    break;
                default:
                    System.err.println("bad option");
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
