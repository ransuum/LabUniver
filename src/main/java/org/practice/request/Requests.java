package org.practice.request;

import org.practice.entity.Task;

import java.util.UUID;

public interface Requests {
    Task createRequest();
    Task updateRequest();
    UUID deleteRequest();
    Task markTaskAsCompletedRequest();
    void showAll();
    Task startTask();
}
