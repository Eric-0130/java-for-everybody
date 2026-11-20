# Java Todo List App — beginner-friendly extension proposal

This is an AI-assisted modification prepared against the **existing** open-source Todo List App maintained in [Kalutu/java-for-everybody](https://github.com/Kalutu/java-for-everybody/tree/main/Todo%20List%20App).

**Original authorship remains with the upstream project.** Do not claim you created the original application or contributed upstream until you submit a pull request. Review and customize these changes before including them on a resume.

## Changes compared with upstream

- Preserved the original `RunTodoListApp(String[] args)` method used by the upstream `menu.java`.
- Added a small `Task` object with a description and completion flag; tasks now display as `[ ]` or `[x]`.
- Added menu option **3** to toggle a task between complete and incomplete.
- Added menu option **4** to search task descriptions using case-insensitive substring matching.
- Handled text input safely so nonnumeric menu or task-number input does not crash the app.
- Rejected empty task descriptions and search keywords.
- Added a standalone `main` method and a testable `run(Scanner, PrintStream)` method.
- Added **13 lightweight tests** without external dependencies.

Original add, remove and list features remain. The **Exit** option has moved from 3 to 5.

## Run (JDK 11+)

From the `Todo List App/` directory:

```bash
javac TodoListApp.java
java TodoListApp
```

Example interaction:

```text
1. Add item      -> Study Java
3. Mark complete -> 1
4. Search items  -> java
1. [x] Study Java
1 matching task(s).
```

## Tests

From the same directory:

```bash
javac TodoListApp.java TodoListAppTest.java
java TodoListAppTest
```

Expected: `All 13 tests passed.`

## Contribution note

This is a **draft contribution**, not a merged pull request. The original project README welcomes PRs. Credit remains with the original author, Kalutu. The proposed change was generated with AI assistance and should be reviewed, tested, and further customized by the person submitting the PR.
