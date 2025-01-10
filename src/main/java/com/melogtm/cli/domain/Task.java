package com.melogtm.cli.domain;

import com.melogtm.cli.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Task {
    private String text;
    private TaskStatus status;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Task(String text, LocalDateTime created, LocalDateTime updated) {
        this.text = text;
        this.status = TaskStatus.TO_DO;
        this.created = created;
        this.updated = updated;
    }
}
