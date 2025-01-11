package com.melogtm.cli;

import com.melogtm.cli.enums.TaskOperations;
import com.melogtm.cli.enums.TaskStatus;
import com.melogtm.cli.service.TaskService;
import com.melogtm.cli.template.VisualCLI;

public class Todo {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(VisualCLI.sendHelpMessage());
            return;
        }

        try {
            TaskOperations operation = TaskOperations.valueOf(args[0].toUpperCase());

            switch (operation) {
                case ADD:
                    TaskService.addTask(args[1]);
                    break;
                case UPDATE:
                    TaskService.updateTask(args[1], args[2]);
                    break;
                case DELETE:
                    TaskService.deleteTask(args[1]);
                    break;
                case MARK_IN_PROGRESS:
                    TaskService.markIn(args[1], TaskStatus.IN_PROGRESS);
                    break;
                case MARK_DONE:
                    TaskService.markIn(args[1], TaskStatus.DONE);
                    break;
                case LIST:
                    if (args.length > 1) TaskService.listTasks(TaskOperations.ListFlags.valueOf(
                            args[1].toUpperCase()));
                    else TaskService.listTasks(null);

                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("It seems that you've missed some arguments...");
            System.out.println("Please check the help message below:");
            System.out.println(VisualCLI.sendHelpMessage());
        }
    }
}
