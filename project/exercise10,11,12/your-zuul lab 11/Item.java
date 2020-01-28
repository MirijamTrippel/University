import java.util.HashMap;
import java.util.Set;
/**
 * Class Item - a item in an adventure game.
 *
 * This class is used to process all items used
 * in the game.
 * 
 * @author  FD YS
 * @version 1.0
 */
 

public class Item {
    
    /*
     * Fields
     */
    private int itemID;
    private int itemRoom;
    private String itemName;
    private int itemValue;
    private String itemDescription;
    private HashMap <String, Room> exits;
    
    
    public Item(String itemDescription){
        this.itemDescription = itemDescription;
        exits = new HashMap<>();
    }
    
    public void createItem(){
        Item apple, torch, battery, compass, map, candle, stone, saw, dice, bomb;
        
        apple = new Item("a perfectly red apple");
        torch = new Item("a inoperative torch(looks like ther is no battery inside)");
        battery = new Item("a single AA battery");
        compass = new Item("contains four directions: east, west, north, south");
        map = new Item("map of the university");
        candle = new Item("a vanilla candle");
        stone = new Item("a stone for decoration");
        saw = new Item("a small saw");
        dice = new Item("there are numbers from 1 to 12 on it");
        bomb = new Item("bomb with glitter");
    }
    /**
     * Set the item ID
     * @param int set item
     */
    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }
    
    /**
     * Return the itemID
     * @return int
     */
    public int getItemID()
    {
        return itemID;
    }
    
    /**
     * Set the location of the item based on the roomID
     * @param int set itemRoom
     */
    public void setItemRoom(int itemRoom)
    {
        this.itemRoom = itemRoom;
    }
    
    /**
     * Return the roomID of the item
     * @return int
     */
    public int getItemRoom()
    {
        return itemRoom;
    }
    
    /**
     * Set the item name
     * @param String set itemName
     */
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    /**
     * Return the name of the item
     * @return String
     */
    public String getItemName()
    {
        return itemName;
    }
    
    /**
     * Set the value of the item
     * 0 is uselass, 1 is quest item
     * @param int 
     */
    public void setItemValue(int itemValue)
    {
        this.itemValue = itemValue;
    }
    
    /**
     * Return the value of the item
     * @return int
     */
    public int getItemValue()
    {
        return itemValue;
    }
    
    /**
     * Return the description of the item
     * @return String
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    
}