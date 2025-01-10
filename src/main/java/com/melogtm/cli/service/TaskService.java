package com.melogtm.cli.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.melogtm.cli.config.ObjectMapperFix;
import com.melogtm.cli.domain.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

public class TaskService {

    // Constants
    private static final ObjectMapper objectMapper = ObjectMapperFix.getObjectMapper();
    private static final String JSON_LOCATION = System.getProperty("user.dir") + "/tasks.json";
    //public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); - will be used
    //in the future no worries
    private static final File file = new File(JSON_LOCATION);

    private static int obtainLastId() {
        if (!file.exists() || file.length() == 0) return 0;

        try {
            // rootNode reads our entire JSON file
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(file);
            Map.Entry<String, JsonNode> lastEntry = null;

            // Iterating -- maybe will be useful
            for (Iterator<Map.Entry<String, JsonNode>> it = rootNode.fields(); it.hasNext(); ) {
                lastEntry = it.next();
            }

            assert lastEntry != null;
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
            ObjectNode rootNode;

            if (id == 0) rootNode = objectMapper.createObjectNode();
            else rootNode = (ObjectNode) objectMapper.readTree(file);

            LocalDateTime created = LocalDateTime.now();
            LocalDateTime updated = LocalDateTime.now();

            Task new_task = new Task(text, created, updated);
            // converts our class into json string
            String task_json = objectMapper.writeValueAsString(new_task);
            // puts our json inside the rootNode ({})
            rootNode.set(String.valueOf(++id), objectMapper.readTree(task_json));
            // Prettier json (^v^)
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
        } catch (IOException j) {
            System.out.println("An error occurred while adding a task: " + j.getMessage());
        }
    }
}
