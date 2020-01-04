/**
 * xXx
 */


public enum CommandWords
{
    GO("go"), HELP("quit"), QUIT("help"), UNKNOWN(null);
    //private static final String[] validCommands = {"go","quit","help" };
    String word;
    private static final String[] validCommands = {
        "go", "quit", "help"
    };
    /**
     * Constructor - initialise the command words.
     */
    private CommandWords(String word)
    {    
        this.word = word;
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for (CommandWords cw : value()){
            if(cw.equals(aString))
                return true;
        }
        return false;
    }
}
