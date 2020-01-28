/**

 * Enumeration of valid Commands in the Game.

 *

 */

public enum CommandWord

{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    LOOK("look"),
    EAT("eat"),
    AMILATE("amILate"),
    BACK("back");
    
    private String commandString;

    CommandWord(String commandString){
        this.commandString = commandString;
    }
    public String toString(){
        return commandString;
    }
}