package org.practice;

import org.practice.controller.TaskController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskController taskController = new TaskController();
        taskController.start();
    }
}