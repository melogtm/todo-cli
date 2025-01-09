package template;

public class VisualCLI {

    public VisualCLI() {}

    public String confirmsSuccessfulAddition(Integer id) {
        return "Task with id " + id + " added successfully!";
    }

    public String sendHelpMessage() {
        return "Usage: todo [operation] [arguments]\n" +
                "Operations:\n" +
                "add [text] - Add a new task\n" +
                "update [id] [text] - Update a task\n" +
                "delete [id] - Delete a task\n" +
                "mark_in_progress [id] - Mark a task as in progress\n" +
                "mark_done [id] - Mark a task as done\n" +
                "list - List all tasks\n" +
                "list [flag*] - List tasks with a specific flag\n" +
                "*Flags:\n" +
                "done - List all tasks that are done\n" +
                "todo - List all tasks that are to do\n" +
                "in-progress - List all tasks that are in progress";
    }

}
