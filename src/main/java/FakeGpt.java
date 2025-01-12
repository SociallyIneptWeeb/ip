public class FakeGpt {

    public static void printHorizontalLine() {
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────");
    }
    public static void main(String[] args) {
        String name = "FakeGPT";
        String logo = "\n" +
                "________________   ____  __.___________ _____________________________\n" +
                "\\_   _____/  _  \\ |    |/ _|\\_   _____//  _____/\\______   \\__    ___/\n" +
                " |    __)/  /_\\  \\|      <   |    __)_/   \\  ___ |     ___/ |    |   \n" +
                " |     \\/    |    \\    |  \\  |        \\    \\_\\  \\|    |     |    |   \n" +
                " \\___  /\\____|__  /____|__ \\/_______  /\\______  /|____|     |____|   \n" +
                "     \\/         \\/        \\/        \\/        \\/                     \n";

        System.out.println(logo);
        printHorizontalLine();
        System.out.printf("Hello! I'm %s!\nWhat can I do for you?\n", name);
        printHorizontalLine();
        System.out.print("Bye. Hope to see you again soon!\n");
    }
}
