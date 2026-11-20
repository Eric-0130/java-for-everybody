import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Beginner-friendly extension of Kalutu/java-for-everybody's Todo List App.
 * Original author / project: https://github.com/Kalutu/java-for-everybody
 * AI-assisted modifications prepared for review and further personal work.
 */
public class TodoListApp {
    /** A task has a description and a completion flag. */
    private static final class Task {
        private final String description;
        private boolean completed;

        Task(String description) {
            this.description = description;
            this.completed = false;
        }

        String display() {
            return (completed ? "[x] " : "[ ] ") + description;
        }
    }

    /** Preserve the entry point used by the upstream repository's menu.java. */
    public static void RunTodoListApp(String[] args) {
        run(new Scanner(System.in), System.out);
    }

    /** Also let users run this file on its own. */
    public static void main(String[] args) {
        RunTodoListApp(args);
    }

    /** Package-visible to allow small automated tests without external libraries. */
    static void run(Scanner scanner, PrintStream out) {
        ArrayList<Task> todoList = new ArrayList<>();
        while (true) {
            out.println("==== Todo List ====");
            for (int i = 0; i < todoList.size(); i++) {
                out.println((i + 1) + ". " + todoList.get(i).display());
            }
            if (todoList.isEmpty()) {
                out.println("No tasks yet.");
            }
            out.println("===================");
            out.println("1. Add item");
            out.println("2. Remove item");
            out.println("3. Mark item completed / incomplete");
            out.println("4. Search items");
            out.println("5. Exit");
            out.print("Enter choice: ");

            String choice = readLine(scanner);
            if (choice == null) {
                out.println("Exiting Todo List App.");
                return;
            }
            switch (choice.trim()) {
                case "1":
                    out.print("Enter item to add: ");
                    String item = readLine(scanner);
                    if (item == null) {
                        out.println("Exiting Todo List App.");
                        return;
                    }
                    if (item.trim().isEmpty()) {
                        out.println("Task description cannot be empty.");
                    } else {
                        todoList.add(new Task(item.trim()));
                        out.println("Item added!");
                    }
                    break;
                case "2":
                    out.print("Enter item number to remove: ");
                    Integer removalNumber = readNumber(scanner);
                    if (removalNumber == null || !validIndex(removalNumber, todoList.size())) {
                        out.println("Invalid item number.");
                    } else {
                        todoList.remove(removalNumber - 1);
                        out.println("Item removed!");
                    }
                    break;
                case "3":
                    out.print("Enter item number to toggle completion: ");
                    Integer completionNumber = readNumber(scanner);
                    if (completionNumber == null || !validIndex(completionNumber, todoList.size())) {
                        out.println("Invalid item number.");
                    } else {
                        Task task = todoList.get(completionNumber - 1);
                        task.completed = !task.completed;
                        out.println(task.completed ? "Item completed!" : "Item marked incomplete!");
                    }
                    break;
                case "4":
                    out.print("Search keyword: ");
                    String keyword = readLine(scanner);
                    if (keyword == null) {
                        out.println("Exiting Todo List App.");
                        return;
                    }
                    if (keyword.trim().isEmpty()) {
                        out.println("Search keyword cannot be empty.");
                        break;
                    }
                    int matches = 0;
                    String normalized = keyword.trim().toLowerCase(Locale.ROOT);
                    for (int i = 0; i < todoList.size(); i++) {
                        Task task = todoList.get(i);
                        if (task.description.toLowerCase(Locale.ROOT).contains(normalized)) {
                            out.println((i + 1) + ". " + task.display());
                            matches++;
                        }
                    }
                    if (matches == 0) {
                        out.println("No matching tasks.");
                    } else {
                        out.println(matches + " matching task(s).");
                    }
                    break;
                case "5":
                    out.println("Exiting Todo List App.");
                    return;
                default:
                    out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static String readLine(Scanner scanner) {
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    /** Invalid or nonnumeric input returns null instead of crashing the app. */
    private static Integer readNumber(Scanner scanner) {
        String line = readLine(scanner);
        if (line == null) {
            return null;
        }
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static boolean validIndex(int number, int size) {
        return number >= 1 && number <= size;
    }
}
