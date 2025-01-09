import template.VisualCLI;

public class Todo {

    public static void main(String[] args) {
        VisualCLI terminal = new VisualCLI();

        if (args.length == 0) {
            System.out.println(terminal.sendHelpMessage());
            return;
        }

        System.out.println("oi!");
    }

}
