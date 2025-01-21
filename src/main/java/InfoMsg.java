public enum InfoMsg {
    DIVIDER("────────────────────────────────────────────────────────────────────────────────────────"),
    INTRO("""
            Ray: H-Hey! It's not like I want to introduce myself or anything, but... my name is
            Sunder Ray. (¬`‸´¬) W-What do you want me to do for you, huh? It's not like I care
            or anything! (,,>.<,,) b-baka!"""),
    END("""
            Sure, bye or whatever. I-I mean, it's not like I want to see you again or
            anything... But, well, if you show up, I guess it wouldn’t be that bad.
            S-See you soon, maybe! (｡>\\\\<)"""),
    NO_TASKS("""
            Hmph, you don’t have any tasks right now. Not that I’m impressed or anything!
            I guess even you can keep things under control... sometimes."""),
    ADDED_TASK("I went ahead and added the task. I just have to remember it for you, right?"),
    NUM_TASKS("Now you have %d %s in the list, in case you were wondering.%n"),
    LIST_TASKS("""
            H-Here! These are the tasks you told me to remember. It's not like I wanted to help
            you or anything. I just didn’t want you messing things up, okay?"""),
    MARK_TASK("I've %sed this task for you. You're welcome I guess!%n"),
    DELETE_TASK("""
            F-Fine, I’ve deleted the task for you!
            I just didn’t want to see it cluttering things up, okay?!"""),
    LOAD_DATA_FILE("""
             I’ve loaded your saved tasks and it looks like you’ve got %d %s, okay?
             Just don’t mess it up from here!%n""");

    private final String message;

    InfoMsg(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
