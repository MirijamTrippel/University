
/**
 * Die Farben meiner Katzen. 
 * Kommt eine neue Katze mit neuer Farbe hinzu, kann ich die
 * neue Farbe hier hinzufügen.
 */
public enum FurrColor
{
    BLACK("Charcoal black from head to toe"),
    BLACKANDWHITE("Black with a white patch around nose and mouth") ,
    TIGER("Brown and black stripes");
    
    private String description;
    
    private FurrColor(String description) {
        this.description = description;
    }
    
    public String toString() {
        return description;
    }
}
