public class World
{   
   private Room currentRoom;
   private World world;
    
   public World() // initialise all rooms
   {
       world.createRoom();
       world = new World();
   }

   private void createRoom()
   {
       Room outside, theater, pub, lab, office;
       // create the rooms
       outside = new Room("outside the main entrance of the university");
       theater = new Room("in a lecture theater");
       pub = new Room("in the campus pub");
       lab = new Room("in a computing lab");
       office = new Room("in the computing admin office");
    
       // initialise room exits
       outside.setExits(null, theater, lab, pub);
       theater.setExits(null, null, null, outside);
       pub.setExits(null, outside, null, null);
       lab.setExits(outside, office, null, null);
       office.setExits(null, null, null, lab);
    
       currentRoom = outside;  // start game outside
   }
   
   
   
}
