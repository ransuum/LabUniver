package org.practice.entity.req;

import org.practice.entity.Task;

import java.time.LocalDate;

public class TaskUpdate {
    private String title;
    private String description;
    private byte priority;
    private LocalDate dueDate;
    private int count;

    public TaskUpdate(builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.dueDate = builder.dueDate;
        this.count = builder.count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class builder {
        private String title;
        private String description;
        private byte priority;
        private LocalDate dueDate;
        private int count;

        public builder title(String title) {
            this.title = title;
            return this;
        }

        public builder description(String description) {
            this.description = description;
            return this;
        }

        public builder priority(byte priority) {
            this.priority = priority;
            return this;
        }

        public builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public builder count(int count) {
            this.count = count;
            return this;
        }

        public TaskUpdate build() {
            return new TaskUpdate(this);
        }
    }
}
