
/**
 * Diese Aufzählung von Katzen ist schon fortgeschrittener.
 * Ich habe einen PRIVATEN! Konstruktor definiert, bei dem
 * den Katzen verschiedene Werte zugewiesen werden können.
 */
public enum MyCatExtended
{
    /* Enumeration of Cats
       They are calling the private constructor.
       See how I use yet another enum? FurrColor!
       */
    POLLY("Polly", 4, FurrColor.BLACKANDWHITE),
    CHARLY("Charly", 1, FurrColor.TIGER),
    CINDY("Cindy", 10, FurrColor.BLACK);
    
    /*Field Variables*/
    private String name;
    private int age;
    private FurrColor color;
    
    /*Private Constructor*/
    private MyCatExtended(String name, int age, FurrColor color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }
    
    /*A custom toString method
       Note how I make use of the FurrColor toString method?*/
    public String toString() {
        return name + " is " + age + " years old and looks like so: "
                    + color.toString();
    }
}
