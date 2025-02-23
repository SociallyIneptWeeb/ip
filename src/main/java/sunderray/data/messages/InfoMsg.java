package sunderray.data.messages;

/**
 * Contains informational messages to display to the user.
 */
public class InfoMsg {
    public static final String INTRO = """
            H-Hey! It's not like I want to introduce myself or anything, but... my name is \
            Sunder Ray. W-What do you want me to do for you, huh? It's not like I care \
            or anything! (,,>.<,,) b-baka!""";
    public static final String END = """
            Sure, bye or whatever. I-I mean, it's not like I want to see you again or \
            anything... But, well, if you show up, I guess it wouldn't be that bad. \
            S-See you soon, maybe! (>\\\\<)""";
    public static final String NO_TASKS = """
            Hmph, you don't have any tasks right now. Not that I'm impressed or anything! \
            I guess even you can keep things under control... sometimes.""";
    public static final String ADDED_TASK =
            "I went ahead and added the task. I just have to remember it for you, right?";
    public static final String NUM_TASKS = "Now you have %d %s in the list, in case you were wondering.";
    public static final String LIST_TASKS = """
            H-Here! These are the tasks you told me to remember. Its not like I wanted to help \
            you or anything. I just didn't want you messing things up, okay?""";
    public static final String MARK_TASK = "I've %sed this task for you. You're welcome I guess!";
    public static final String DELETE_TASK = """
            F-Fine, I've deleted the task for you! \
            I just didn't want to see it cluttering things up, okay?!""";
    public static final String LIST_MATCHED_TASKS = """
            F-Fine, here are the tasks that match your keyword... \
            Don't go thinking I did this for you or anything!""";
    public static final String NO_TASKS_MATCHED = """
            Hmph, none of your tasks matched the keyword you gave me!""";
    public static final String LOAD_DATA_FILE = """
             I've loaded your saved tasks and it looks like you've got %d %s, okay? \
             Just don't mess it up from here!""";
}
