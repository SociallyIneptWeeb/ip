import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SunderRay {
    public static String UNRECOGNIZED_RESPONSE = """
            W-What?! I couldn’t understand what you said, okay?
            It’s not like it’s my fault or anything!
            Just… ugh, repeat it already, and don’t make me wait!""";

    public static void printDivider() {
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────");
    }

    public static void introduce() {
        String name = "Sunder Ray";
        System.out.printf("""
                Ray: H-Hey! It's not like I want to introduce myself or anything, but... my name is
                %s. (¬`‸´¬) W-What do you want me to do for you, huh? It's not like I care
                or anything! (,,>.<,,) b-baka!%n""", name);
    }

    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("I went ahead and added the task. I just have to remember it for you, right?");
        System.out.println(task);
        System.out.printf(
                "You have %d %s in the list, in case you were wondering.%n",
                tasks.size(),
                tasks.size() > 1 ? "tasks" : "task");
    }

    public static void converse() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        Pattern eventpattern = Pattern.compile("event (.+?) /from (.+?) /to (.+)");
        Pattern deadlinePattern = Pattern.compile("deadline (.+?) /by (.+)");

        loop: while (true) {
            printDivider();
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            printDivider();

            System.out.print("Ray: ");

            String[] words = userInput.split(" ", 2);
            String action = words[0];
            int taskId;
            Task task;
            Matcher matcher;

            switch (action) {
                case "bye":
                    break loop;

                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("""
                                Hmph, you don’t have any tasks right now. Not that I’m impressed or anything!
                                I guess even you can keep things under control... sometimes.""");
                    } else {
                        System.out.println("""
                                H-Here! These are the tasks you told me to remember. It's not like I wanted to help
                                you or anything. I just didn’t want you messing things up, okay?""");

                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                        }
                    }
                    break;

                case "mark":
                    // Fallthrough
                case "unmark":
                    try {
                        taskId = Integer.parseInt(words[1]) - 1;
                        task = tasks.get(taskId);
                        task.setIsDone(action.equals("mark"));
                        System.out.printf("""
                                I've %sed this task for you. You're welcome I guess!%n""", action);
                        System.out.println(task);
                    } catch (NumberFormatException e) {
                        System.out.printf("""
                                Ugh, seriously? Be more clear! I can’t read your mind, you know!
                                Just… specify which task you want to %1$s as done in this format: %1$s <task-id>
                                """, action);
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("""
                                Ugh, seriously?! Just pick a valid task ID already!
                                It’s not like I enjoy pointing out your mistakes or anything…""");
                    }
                    break;

                case "todo":
                    if (words.length < 2) {
                        System.out.println("""
                                H-Hey! If you’re going to add a todo task, at least do it properly!
                                Give me its details in this format: todo <description>""");
                        break;
                    }
                    task = new ToDo(words[1]);
                    addTask(tasks, task);
                    break;

                case "deadline":
                    matcher = deadlinePattern.matcher(userInput);
                    if (matcher.find()) {
                        task = new Deadline(matcher.group(1), matcher.group(2));
                        addTask(tasks, task);
                    } else {
                        System.out.println("""
                                H-Hey! If you’re going to add a deadline, at least do it properly!
                                Give me its details in this format: deadline <description> /by <when>""");
                    }
                    break;

                case "event":
                    matcher = eventpattern.matcher(userInput);
                    if (matcher.find()) {
                        task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                        addTask(tasks, task);
                    } else {
                        System.out.println("""
                                H-Hey! If you’re going to add an event, at least do it properly!
                                Give me its details in this format: event <description> /from <when> /to <when>""");
                    }
                    break;

                default:
                    System.out.println(UNRECOGNIZED_RESPONSE);
            }
        }
    }

    public static void sayBye() {
        System.out.println("""
                Sure, bye or whatever. I-I mean, it's not like I want to see you again or
                anything... But, well, if you show up, I guess it wouldn’t be that bad.
                S-See you soon, maybe! (｡>\\\\<)""");
    }

    public static void main(String[] args) {
        introduce();
        converse();
        sayBye();
    }
}
