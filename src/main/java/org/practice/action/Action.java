package org.practice.action;

import org.practice.entity.Task;

import java.util.Scanner;

public class Action extends Thread{
    private final Task task;

    public Action(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (task.getCount() > 0) {
            System.out.print("Enter a number to deduct from the task count: ");
            int userInput = scanner.nextInt();
            task.setCount(task.getCount() - userInput);
            System.out.println("Remaining count: " + task.getCount());
        }
    }
}
