import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/** Lightweight tests with no JUnit or Maven requirement. */
public class TodoListAppTest {
    private static int passed = 0;

    public static void main(String[] args) throws Exception {
        test("add and display", "1\nRead CSE 12 notes\n5\n", "1. [ ] Read CSE 12 notes");
        test("reject empty description", "1\n   \n5\n", "Task description cannot be empty.");
        test("mark task complete", "1\nStudy Java\n3\n1\n5\n", "1. [x] Study Java");
        test("toggle task back to incomplete", "1\nStudy Java\n3\n1\n3\n1\n5\n", "Item marked incomplete!");
        test("case insensitive keyword search", "1\nStudy JAVA\n4\njava\n5\n", "1 matching task(s).");
        test("search without match", "1\nStudy Java\n4\npython\n5\n", "No matching tasks.");
        test("reject blank search", "4\n \n5\n", "Search keyword cannot be empty.");
        test("remove existing task", "1\nRead notes\n2\n1\n5\n", "Item removed!");
        test("reject out of bounds task number", "3\n99\n5\n", "Invalid item number.");
        test("nonnumeric menu input does not crash", "hello\n5\n", "Invalid choice. Please try again.");
        test("nonnumeric removal input does not crash", "1\nA\n2\nabc\n5\n", "Invalid item number.");
        test("no tasks handled", "5\n", "No tasks yet.");
        test("end-of-file exits gracefully", "", "Exiting Todo List App.");
        System.out.println("All " + passed + " tests passed.");
    }

    private static void test(String name, String input, String expected) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(buffer, true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(input)) {
            TodoListApp.run(scanner, out);
        }
        String result = buffer.toString(StandardCharsets.UTF_8);
        if (!result.contains(expected)) {
            throw new AssertionError(name + " expected '" + expected + "' but got:\n" + result);
        }
        passed++;
        System.out.println("[PASS] " + name);
    }
}
