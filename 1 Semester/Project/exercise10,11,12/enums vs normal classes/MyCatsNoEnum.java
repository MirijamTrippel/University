import java.util.HashMap;
/**
 * Creates a collection of all my cats
 */
public class MyCatsNoEnum
{
    private HashMap<String, MyCatNoEnum> myCats;
    
    /* Constructor */
    public MyCatsNoEnum() {
        myCats = new HashMap<>();
        myCats.put("POLLY", new MyCatNoEnum("Polly", 4, 
        "Black with a white patch around nose and mouth"));
        myCats.put("CHARLY", new MyCatNoEnum("Charly", 1, 
        "Brown and black stripes"));
        myCats.put("CINDY", new MyCatNoEnum("Cindy", 10, 
        "Charcoal black from head to toe"));
    }
    
    /* Getter */
    public HashMap<String, MyCatNoEnum> getCats() {
        return myCats;
    }
}
