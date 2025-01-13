import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SunderRay {
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

    public static void converse() {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        loop: while (true) {
            printDivider();
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            printDivider();

            System.out.print("Ray: ");

            switch (userInput) {
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

                case "":
                    System.out.print("What was that? If you have something to say, then speak up!");
                    break;

                default:
                    if (userInput.startsWith("mark")) {
                        // TODO: Add exception handling
                        int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        Task task = tasks[taskId];
                        task.setIsDone(true);
                        System.out.print("""
                                W-Well, nice job, I guess! I marked this task as done for you—
                                Not that I’m impressed or anything!
                                """);
                        System.out.println(task);
                    } else if (userInput.startsWith("unmark")) {
                        // TODO: Add exception handling
                        int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        Task task = tasks[taskId];
                        task.setIsDone(false);
                        System.out.print("""
                                I’ve marked this task as not done yet. It’s not like I care if you finish it or not—
                                I just didn’t want to see it sitting there all messy!
                                S-So hurry up and deal with it already, okay?
                                """);
                        System.out.println(task);
                    } else {
                        tasks[numTasks] = new Task(userInput);
                        numTasks++;
                        System.out.printf("""
                                Ugh, fine! I went ahead and added the task '%s' for you, okay?
                                I just have to remember it for you, right?
                                """, userInput);
                    }
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
