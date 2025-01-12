import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
                H-Hey! It's not like I want to introduce myself or anything, but... my name is %s. (¬`‸´¬)
                W-What do you want me to do for you, huh? It's not like I care or anything! (💢,,>﹏<,,) b-baka!
                """, name);
        printDivider();
    }

    public static void sayBye() {
        System.out.println("""
                Ugh, fine! Bye or whatever. I-I mean, it's not like I want to see you again or anything...
                But, well, if you show up, I guess it wouldn’t be that bad. S-See you soon, maybe! ૮₍ ˶>⤙<˶  ₎ა""");
    }

    public static void main(String[] args) {
        introduce();
        sayBye();
    }
}
