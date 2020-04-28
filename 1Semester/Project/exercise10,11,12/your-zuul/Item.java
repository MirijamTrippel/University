import java.util.HashMap;
import java.util.Set;
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private String itemDescription;
    private String itemName;
    private int itemWeight;
    private HashMap <String, Room> items;
    

    /**
     * Creats the description of the items that are in this room.
     * @param description The room's description.
     */
    public Item(String itemName, String itemDescription, int itemWeight) 
    {
        this.itemDescription = itemDescription;
        this.itemName = itemName;
        this.itemWeight = itemWeight;
    }
    
    public Room getItem (Item item){
        return items.get(item);
    }
    
    /**
     * @return The description of the item.
     */
    public String getItemDescription(){
        return itemDescription;
    }
    
    /**
     * @return The name of the item.
     */
    public String getItemName(){
        return itemName;
    }
    
    /**
     * @return The weigth of the item.
     */
    public int getItemWeight(){
        return itemWeight;
    }
    
}
