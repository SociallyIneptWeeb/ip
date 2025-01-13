import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/picture.txt"));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Picture file was not found.");
            return;
        } catch (IOException e) {
            System.out.println("Error reading picture file.");
            return;
        }

        printDivider();
        System.out.printf("""
                Ray: H-Hey! It's not like I want to introduce myself or anything, but... my name is
                %s. (¬`‸´¬) W-What do you want me to do for you, huh? It's not like I care
                or anything! (💢,,>﹏<,,) b-baka!
                """, name);
    }

    public static void addTask(Task[] tasks, Task task, int numTasks) {
        tasks[numTasks] = task;
        System.out.println("I went ahead and added the task. I just have to remember it for you, right?");
        System.out.println(task);
        System.out.printf(
                "You have %d %s in the list, in case you were wondering.\n",
                numTasks + 1,
                numTasks > 0 ? "tasks" : "task");
    }

    public static void converse() {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;
        Pattern eventpattern = Pattern.compile("event (.+?) /from (.+?) /to (.+)");
        Pattern deadlinePattern = Pattern.compile("deadline (.+?) /by (.+)");

        loop: while (true) {
            printDivider();
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            printDivider();

            System.out.print("Ray: ");

            String action = userInput.split(" ")[0];
            int taskId;
            Task task;
            Matcher matcher;

            switch (action) {
                case "bye":
                    break loop;

                case "list":
                    if (numTasks == 0) {
                        System.out.println("""
                                Hmph, you don’t have any tasks right now. Not that I’m impressed or anything!
                                I guess even you can keep things under control... sometimes.""");
                    } else {
                        System.out.println("""
                                H-Here! These are the tasks you told me to remember. It's not like I wanted to help
                                you or anything. I just didn’t want you messing things up, okay?
                                S-So don’t get the wrong idea!
                                """);

                        for (int i = 0; i < numTasks; i++) {
                            System.out.printf("%d. %s\n", i + 1, tasks[i]);
                        }
                    }
                    break;

                case "mark":
                    try {
                        taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        task = tasks[taskId];
                        task.setIsDone(true);
                        System.out.print("""
                                W-Well, nice job, I guess! I marked this task as done for you—
                                Not that I’m impressed or anything!
                                """);
                        System.out.println(task);
                    } catch (NumberFormatException e) {
                        System.out.println(UNRECOGNIZED_RESPONSE);
                    }
                    break;

                case "unmark":
                    try {
                        taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        task = tasks[taskId];
                        task.setIsDone(false);
                        System.out.print("""
                                I’ve marked this task as not done yet. It’s not like I care if you finish it or not—
                                I just didn’t want to see it sitting there all messy!
                                S-So hurry up and deal with it already, okay?
                                """);
                        System.out.println(task);
                    } catch (NumberFormatException e) {
                        System.out.println(UNRECOGNIZED_RESPONSE);
                    }
                    break;

                case "todo":
                    task = new ToDo(userInput.split(" ", 2)[1]);
                    addTask(tasks, task, numTasks);
                    numTasks++;
                    break;

                case "deadline":
                    matcher = deadlinePattern.matcher(userInput);
                    if (matcher.find()) {
                        task = new Deadline(matcher.group(1), matcher.group(2));
                        addTask(tasks, task, numTasks);
                        numTasks++;
                    } else {
                        System.out.println(UNRECOGNIZED_RESPONSE);
                    }
                    break;

                case "event":
                    matcher = eventpattern.matcher(userInput);
                    if (matcher.find()) {
                        task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                        addTask(tasks, task, numTasks);
                        numTasks++;
                    } else {
                        System.out.println(UNRECOGNIZED_RESPONSE);
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
                S-See you soon, maybe! ૮₍ ˶>⤙<˶  ₎ა""");
    }

    public static void main(String[] args) {
        introduce();
        converse();
        sayBye();
    }
}
