
/**
 * Write a description of class World here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class World
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Room previousRoom;

    /**
     * Constructor for objects of class World
     */
    public World()
    {
        createRooms();
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
        
        // initialise room items
        
        outside.setItems("compass", 150);
        outside.setItems("map", 15);
        theater.setItems("apple", 50);
        theater.setItems("candle", 200);
        theater.setItems("stone", 1000);
        pub.setItems("beer", 500);
        pub.setItems("saw", 300);
        lab.setItems("key", 70);
        lab.setItems("bomb", 700);
        office.setItems("book", 400);
        office.setItems("dice", 5);
       
        
        previousRoom = null;
        currentRoom = outside;  // start game outside
    }
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public Room getPreviousRoom(){
        return previousRoom;
    }
}
