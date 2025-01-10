package com.melogtm.cli.service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskService {

    private static final String JSON_LOCATION = System.getProperty("user.dir") + "/tasks.json";
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public int obtainLastId() {

        File f = new File(JSON_LOCATION);

        if (!f.exists() || f.length() == 0) return 0;

        String lastLine = null;

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(JSON_LOCATION))) {

            do {
                lastLine = br.readLine();
            } while ((lastLine = br.readLine()) != null);


        } catch (IOException e) {
            System.out.println("An error occurred while obtaining the last id: " + e.getMessage());
        }

        assert lastLine != null;

        return Integer.parseInt(lastLine.split(" ")[0]);

    }

    public void addTask(String text) {



        int id = obtainLastId() + 1;
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.json", true))) {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while adding a task: " + e.getMessage());
        }

    }

}
