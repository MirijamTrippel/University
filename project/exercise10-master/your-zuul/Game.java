/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    //----
    public String description;
    
    private String command;
    
    
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    
    private String commandWord;
    private String secondWord;
    
    private static final String[] validCommands = {
        "go", "quit", "help"
    };
    
    //----
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //parser = new Parser();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
    {            
        printWelcome();
        boolean finished = false;
        while (! finished) {
            Command command = Parser.getCommand();
            String output = processCommand(command);
            finished = (null == output);
            if (!finished)
            {
                System.out.println(output);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    //---- play() Part 1
    private void printWelcome() //ln 55 currentRoom.getDecription());
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + getDescription() ); //describes the current room
        System.out.print("Exits: "); //here are the exits listed
            if(currentRoom.northExit != null) {
                System.out.print("north ");
            }
            if(currentRoom.southExit != null) {
                System.out.print("south ");
            }
            if(currentRoom.eastExit != null) {
                System.out.print("east ");
            }
            if(currentRoom.westExit != null) {
                System.out.print("west ");
            }
        System.out.println(); //????? useless?
    }
    public String getDescription() //ln 77
    {
        return description;
    }
    //---- play() Part 2 knows command
    
    
    
    public String getCommandWord()
    {
        return commandWord;
    }
    private String processCommand(string command) //ln59 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            return "I don't know what you mean...";       
        }
        String result = null;
        String commandWord = getCommandWord();
        if (commandWord.equals("help")) {
            result = printHelp();
        }
        else if (commandWord.equals("go")) {
            result = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            result = quit(command);
        }

        return result ;
    }
    public boolean isUnknown() //ln102
    {
        return (commandWord == null);
    }
    public String getCommandWord() //ln106
    {
        return commandWord;
    }
    private String printHelp() //ln108
    {
        return "You are lost. You are alone. You wander"
        +"\n"
        + "around at the university."
        +"\n"
        +"\n"
        +"Your command words are:"
        +"\n"
        +"   go quit help"
        +"\n";
    }
    private String goRoom(Command command) //ln111
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            currentRoom = nextRoom;
            result += "You are " + currentRoom.getDescription()+"\n";
            result += "Exits: ";
            if(currentRoom.northExit != null) {
                result += "north ";
            }
            if(currentRoom.eastExit != null) {
                result += "east ";
            }
            if(currentRoom.southExit != null) {
                result += "south ";
            }
            if(currentRoom.westExit != null) {
                result += "west ";
            }         
        }
        return result + "\n";
    }
    private String quit(Command command) //ln114
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }
    
    
    
    public command(String firstWord, String secondWord)
    {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }
    
    
    
    
    public String getCommand() {
        String inputLine = readLine();
        return getCommand(inputLine);
    }
    private String readLine(){    
        System.out.print("> ");     // print prompt
        return reader.nextLine();  ///xxx?
    }
    
    
    
    public Command getCommand(String inputLine)
    {
        String word1 = null;
        String word2 = null;

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }
    //----
    
    
    
    
    
    
    
    /**
     * This is a further method added by BK to
     * provide a clearer interface that can be tested:
     * Game processes a commandLine and returns output.
     * @param commandLine - the line entered as String
     * @return output of the command
     */
    public String processCommand(String commandLine){
        Command command = parser.getCommand(commandLine);
        return processCommand(command);
    }

    
    

    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private String processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            return "I don't know what you mean...";       
        }
        String result = null;
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            result = printHelp();
        }
        else if (commandWord.equals("go")) {
            result = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            result = quit(command);
        }

        return result ;
    }

    
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private String printHelp() 
    {
        return "You are lost. You are alone. You wander"
        +"\n"
        + "around at the university."
        +"\n"
        +"\n"
        +"Your command words are:"
        +"\n"
        +"   go quit help"
        +"\n";
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private String quit(Command command) 
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }
}
