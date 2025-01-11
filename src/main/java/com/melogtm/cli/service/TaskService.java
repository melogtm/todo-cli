package com.melogtm.cli.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.melogtm.cli.config.ObjectMapperFix;
import com.melogtm.cli.domain.Task;
import com.melogtm.cli.enums.TaskOperations;
import com.melogtm.cli.enums.TaskStatus;
import com.melogtm.cli.template.VisualCLI;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

public class TaskService {

    // Constants
    private static final ObjectMapper objectMapper = ObjectMapperFix.getObjectMapper();
    private static final String JSON_LOCATION = System.getProperty("user.dir") + "/tasks.json";
    public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final File file = new File(JSON_LOCATION);


    private static int obtainLastId() {
        if (!file.exists() || file.length() == 0) return 0;

        try {
            // task_database reads our entire JSON file
            ObjectNode task_database = (ObjectNode) objectMapper.readTree(file);
            Map.Entry<String, JsonNode> lastEntry = null;

            // Iterating -- maybe will be useful
            for (Iterator<Map.Entry<String, JsonNode>> it = task_database.fields(); it.hasNext(); ) {
                lastEntry = it.next();
            }

            if (lastEntry == null) return 0;
            return Integer.parseInt(lastEntry.getKey());

        } catch (IOException e) {
            System.out.println("An error occurred while obtaining the last id: " + e.getMessage());
            return -1;
        }
    }

    public static void addTask(String text) {
        try {
            int id = obtainLastId();
            if (id == -1) return; // Error occurred, custom exceptions will be created
            ObjectNode task_database;

            if (id == 0) task_database = objectMapper.createObjectNode();
            else task_database = (ObjectNode) objectMapper.readTree(file);

            String created = df.format(LocalDateTime.now());
            String updated = df.format(LocalDateTime.now());

            Task new_task = new Task(text, created, updated);
            // converts our class into json string
            String task_json = objectMapper.writeValueAsString(new_task);
            // puts our json inside the rootNode ({})
            task_database.set(String.valueOf(++id), objectMapper.readTree(task_json));
            // Prettier json (^v^)
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, task_database);

            System.out.println("Task added successfully. (ID " + id + ")");
        } catch (IOException j) {
            System.out.println("An error occurred while adding a task: " + j.getMessage());
        }
    }

    public static void updateTask(String id, String text) {
        try {
            ObjectNode task_database = (ObjectNode) objectMapper.readTree(file);

            JsonNode task = task_database.get(id);
            if (task == null) {
                System.out.println("Task with id " + id + " not found.");
                return;
            }

            ObjectNode task_node = (ObjectNode) task;
            task_node.put("text", text);
            task_node.put("updated", LocalDateTime.now().format(df));

            // remember to always put this when performing a write operation
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, task_database);
        } catch (IOException e) {
            System.out.println("An error occurred while updating a task: " + e.getMessage());
        }
    }

    // new_status can be either in progress or done
    public static void markIn(String id, TaskStatus new_status) {
        try {
            ObjectNode task_database = (ObjectNode) objectMapper.readTree(file);

            JsonNode task = task_database.get(id);
            if (task == null) {
                System.out.println("Task with id " + id + " not found.");
                return;
            }

            ObjectNode task_node = (ObjectNode) task;
            task_node.put("status", new_status.toString());
            task_node.put("updated", LocalDateTime.now().format(df));

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, task_database);
        } catch (Exception e) {
            System.out.println("An error occurred while updating status of a task: " + e.getMessage());
        }
    }

    public static void deleteTask(String id) {
        try {
            ObjectNode task_database = (ObjectNode) objectMapper.readTree(file);

            if (!task_database.has(id)) {
                System.out.println("Task with id " + id + " not found.");
                return;
            }

            task_database.remove(id);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, task_database);
        } catch (IOException e) {
            System.out.println("An error occurred while deleting a task: " + e.getMessage());
        }
    }

    public static void listTasks(TaskOperations.ListFlags filter) {
        try {
            ObjectNode task_database = (ObjectNode) objectMapper.readTree(file);

            for (Iterator<Map.Entry<String, JsonNode>> it = task_database.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = it.next();
                ObjectNode task = (ObjectNode) entry.getValue();

                if (filter == null || TaskOperations.ListFlags.valueOf(task.get("status")
                        .asText()) == filter) {
                    System.out.println(VisualCLI.formatFriendlyTaskList(task));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while listing tasks: " + e.getMessage());
        }
    }

}
