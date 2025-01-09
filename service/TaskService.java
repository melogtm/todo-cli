package service;

import java.io.BufferedWriter;
import java.io.IOException;

public class TaskService {

    public void addTask(String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.json", true))) {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while adding a task: " + e.getMessage());
        }
    }

}
