package com.melogtm.cli.template;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Objects;

public class VisualCLI {

    public VisualCLI() {}

    public static String formatFriendlyTaskList(ObjectNode task) {

        String content = task.get("text").asText();
        String status = task.get("status").asText();
        status = Objects.equals(status, "TO_DO") ? "To do" : Objects.equals(status, "IN_PROGRESS")
                ? "In Progress" : "Done";
        String created = task.get("created").asText();
        String updated = task.get("updated").asText();

        return "[*] Task: " + content + " | Status: " + status + " | Created: " + created +
                " | Updated: " + updated;
    }

    public static String sendHelpMessage() {
        return """
                Usage: todo [operation] [arguments]
                Operations:
                add [text] - Add a new task
                update [id] [text] - Update a task
                delete [id] - Delete a task
                mark_in_progress [id] - Mark a task as in progress
                mark_done [id] - Mark a task as done
                list - List all tasks
                list [flag*] - List tasks with a specific flag
                *Flags:
                done - List all tasks that are done
                todo - List all tasks that are to do
                in_progress - List all tasks that are in progress""";
    }

}
