package com.melogtm.cli;

import com.melogtm.cli.service.TaskService;
import com.melogtm.cli.template.VisualCLI;

public class Todo {

    public static void main(String[] args) {
        VisualCLI terminal = new VisualCLI();

        if (args.length == 0) {
            System.out.println(terminal.sendHelpMessage());
            return;
        }

        TaskService.addTask("AMO QUEIJO");
    }
}
