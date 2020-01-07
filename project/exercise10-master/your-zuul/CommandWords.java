/**
 * xXx
 */


public enum CommandWords
{
    GO("go"), HELP("help"), QUIT("quit"), UNKNOWN(null);
    private static final String[] validCommands = {"go","quit","help" };
    String word;
    
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
        for (String cw : validCommands){
            if(cw.equals(aString))
                return true;
        }
        return false;
    }
}
