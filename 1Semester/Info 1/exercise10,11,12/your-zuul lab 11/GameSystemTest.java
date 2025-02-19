
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a Game System Tests - it tests the
 * whole game and the endpoints between user input
 * and output to the console.
 *
 * @author  Barne Kleinen
 */
public class GameSystemTest
{
    private Game game;

    public GameSystemTest()
    {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        game = new Game();
    }

    @Test
    public void testQuit()
    {
        //given: new game
        //when
        String output = game.processCommand("quit");
        //then
        assertEquals(
            "null is the output that signals that the main loop should stop",
            null,output);
    }

    @Test
    public void testHelp()
    {
        //given: new game

        //when
        String output = game.processCommand("help");
        //then
        assertTrue("should print help message containing command words", output.contains("command words"));
        assertTrue("message contains command word go", output.contains("go"));
        assertTrue("message contains command word quit", output.contains("quit"));
        assertTrue("message contains command word help", output.contains("help"));
        assertTrue("message contains command word look", output.contains("look")); // added test for command look
        assertTrue("message contains command word eat", output.contains("eat")); // added test for command eat
    }

    @Test
    public void testUnknownCommand(){
        // given arbitrary game
        // when entering unknown command
        String output = game.processCommand("asdf");
        // then an error message should be returned
        assertTrue("should output error message", output.contains("I don't know what you mean"));
    }
    @Test
    public void testGoSouth()
    {
        //given: new game
        //when
        String output = game.processCommand("go south");
        //then
        assertTrue("should be in computing lab"+output,
            output.contains("computing lab"));
    }

    @Test
    public void testGoNorth()
    {
        //given: new game
        //when
        String output = game.processCommand("go north");
        //then
        assertEquals(true, output.contains("no door"));
    }

    /**
     * version 1: manual test case
     */
    @Test
    public void testGoWithoutDirection()
    {
        //given: new game
        //when
        String output = game.processCommand("go");
        //then
        assertEquals(true, output.contains("Go where"));
    }

    /**
     * version 2: recorded test case
     */
    @Test
    public void goWODirectionShouldShowError()
    {
        assertEquals("Go where?", game.processCommand("go"));
    }

    @Test
    public void completeWalkthrough()
    {
        goAndSee("east",  "lecture theater");
        goAndSee("west",  "main entrance");
        goAndSee("west",  "campus pub");
        goAndSee("east",  "main entrance");
        goAndSee("south", "computing lab");
        goAndSee("east",  "admin office");
        goAndSee("west",  "computing lab");
        goAndSee("north", "main entrance");
    }

    private void goAndSee(String direction, String whatShouldBeContained){
        //when
        String result = game.processCommand("go "+direction);
        //then
        if (!result.contains(whatShouldBeContained))
            fail(result +" does not contain "+whatShouldBeContained);
    }

    @Test
    public void showExits(){
        game.processCommand("go south");
        String result = game.processCommand("go north");
        assertTrue(result.contains("Exits:"));
        assertTrue(result.contains("east"));
        assertTrue(result.contains("south"));
        assertTrue(result.contains("west"));
    }

    @Test
    public void showCommands(){
        game.processCommand("go south");
        String result = game.processCommand("go north");
        assertTrue(result.contains("east"));
        assertTrue(result.contains("south"));
        assertTrue(result.contains("west"));
    }

    @Test
    public void lookTest()
    {
        String result = game.processCommand("look");
        assertTrue(result.contains("You are outside the main entrance of the university.\nExits: east south west"));
    }

    @Test
    public void eatTest()
    {
        String result = game.processCommand("eat");
        assertTrue(result.contains("You have eaten now and are not hungry any more"));
    }

    @Test
    public void amILateTest()
    {
        String result = game.processCommand("amILate");
        assertTrue(result.contains("You are late! Please, move faster"));
    }

    @Test
    public void showItems()
    {
        String result = game.processCommand("look");
        assertTrue(result.contains("map"));
        assertTrue(result.contains("compass"));
    }
    
    @Test
    public void testBack()
    {
        String result = game.processCommand("back");
        assertTrue(result.contains("No room to come back to"));
        result = game.processCommand("go south");
        assertTrue(result.contains("You are in a computing lab.\nExits: east north.\nItems: bomb key"));
        result = game.processCommand("back");
        assertTrue(result.contains("You are outside the main entrance of the university.\nExits: east south west.\nItems: compass map"));
    }
}



    
    
 









