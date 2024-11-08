package org.practice.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private byte priority;
    private LocalDate dueDate;
    private boolean completed;
    private int count;

    private Task(builder builder) {
        this.id = UUID.randomUUID();
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.dueDate = builder.dueDate;
        this.completed = builder.completed;
        this.count = builder.count;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Task {" + "\n" +
                 "   \"id\":" + this.id + "\n" +
                "    \"title\":" + this.title + "\n" +
                "    \"description\":" + this.description + "\n" +
                "    \"priority\":" + this.priority + "\n" +
                "    \"dueDate\":" + this.dueDate + "\n" +
                "    \"completed\":" + this.completed + "\n" +
                ((count == 0) ? "" : "    \"count\":" + this.count + "\n") +
                "}\n";
    }

    public static class builder {
        private String title;
        private String description;
        private byte priority;
        private LocalDate dueDate;
        private boolean completed;
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

        public builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public builder count(int count) {
            this.count = count;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
