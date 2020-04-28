
/**
 * Beschreiben Sie hier die Klasse Cat.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Cat
{
    private String color;
    private int age;
    private String name;
    private int amountOfFishEaten;
    
    public Cat(String myName, String myColor, int myAge,int myAmountOfFishEaten)
    {
        name = myName;
        color = myColor;
        age = myAge;
        amountOfFishEaten = myAmountOfFishEaten;
    }
    
    public void eatFish(int fish){
    amountOfFishEaten = amountOfFishEaten + fish;
    }
}
