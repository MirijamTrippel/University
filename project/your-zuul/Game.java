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
    private CommandWord commandWord;
    private Item item;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExits("east", theater);
        outside.setExits("west", pub);
        outside.setExits("south", lab);
        theater.setExits("west", outside);
        pub.setExits("east", outside);
        lab.setExits("north", outside);
        lab.setExits("east", office);
        office.setExits("west", lab);

        createItems(outside, theater, pub, lab, office);
        currentRoom = outside;  // start game outside
    }

    private void createItems(Room outside, Room theater, Room pub, Room lab, Room office){
        Item key, dice, map, stone, saw, compass, ladder, beer, torch;
        
        key = new Item ("key", "This is a key, it helps to lock and open doors",10);
        dice = new Item ("dice", "This is dice, you can get a random number with it",5);
        map = new Item ("map", "This is a map, it helps you to find places",5);
        stone = new Item ("stone", "This is a stone, it's here for decoration",1000);
        saw = new Item ("saw", "This is a saw, it helps you to cut branches",200);
        compass = new Item ("compass", "This is a compass, it helps you to define where you are at the moment",150);
        ladder = new Item ("ladder", "This is a ladder, it helps you to climb up something",5000);
        beer = new Item ("beer", "This is a beer, you can drink it (if you drink alcohol)",100);
        torch = new Item ("torch", "This is a torch, it helps you to lighten places up",50);
        
        
        outside.setItems(key);
        outside.setItems(map);
        theater.setItems(stone);
        theater.setItems(dice);
        lab.setItems(saw);
        lab.setItems(compass);
        pub.setItems(ladder);
        pub.setItems(beer);
        office.setItems(torch);
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            String output = processCommand(command);
            finished = (null == output);
            if (!finished)
            {
                System.out.println(output);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
  
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
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription()); 
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private String processCommand(Command command) 
    {
        boolean wantToQuit = false;
        String result = null;
        String commandWord = command.getCommandWord();
            if(command.isUnknown()) {
            return"I don't know what you mean...";
        }
        switch(commandWord){
            case "help":
                result = printHelp();
            break;
            case "go":
                result = goRoom(command);
            break;
            case "quit":
                result = quit(command);
            break;
            case "look":
                result = look();
            break;
            case "eat":
                result = eat();
            break;
            case "amILate":
                result = amILate();
            break;
        }return result;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private String printHelp() 
    {   
        String printString = "";
        printString += "You are lost. You are alone. You wander" + "\n";
        printString += "around at the university." +"\n" + "\n" ;
        printString +="Your command words are:" + "\n";
        printString += parser.showCommands();
        return printString;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            currentRoom = nextRoom;
            result += currentRoom.getLongDescription(); 
            result += currentRoom.getExitString()+"\n";
        }
        return result;
    }
    
    //Task 3
    /**
     * "Look" was entered. The output contains information about the room, 
     * where the user currently is.
     */
    private String look()
    {
        String result = currentRoom.getLongDescription();
        return result;
    }
    
    //Task 4
    /**
     * "Eat" was entered. Can be called by the user, 
     * only if they have food.
     */
    private String eat()
    {
        return "You have eaten now and are not hungry any more";
    }
    
    /**
     * "Eat" was entered. Can be called by the user, 
     * only if they have food.
     */
    private String amILate()
    {
        return "You are late! Please, move faster";
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return null, if this command quits the game
     */
    private String quit(Command command) 
    {
        if(command.hasSecondWord()) {
            return"Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }
}
