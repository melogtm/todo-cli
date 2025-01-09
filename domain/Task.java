package domain;

import enums.TaskStatus;

import java.time.LocalDateTime;

public class Task {
    private Integer id;
    private String text;
    private TaskStatus status;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Task() {}

    public Task(Integer id, String text, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.text = text;
        this.status = TaskStatus.TO_DO;
        this.created = created;
        this.updated = updated;
    }

    public Task(Integer id, String text, TaskStatus status, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + " " + text + " - " + status + " - Created at: " + created + " - Updated at: " + updated;
    }
}
