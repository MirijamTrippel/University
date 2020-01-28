import java.util.HashMap;
/**
 * Just to demonstrate how to iterate over cats
 */
public class Cats
{
    /* List cats using enums */
    public static void listAllCats()
    {
        System.out.println("Cats listed with enums:");
        for (MyCatExtended cat : MyCatExtended.values()) {
            System.out.println(cat.toString());
        }
    }
    
    public static void printCatPolly() {
        /*Try writing polly or Polly instead of POLLY*/
        System.out.println(MyCatExtended.POLLY.toString());
    }
    
    /* List cats NOT using enums */
    public static void listAllCatsNoEnum() 
    {
       System.out.println("Cats listed WITHOUT enums:");

       MyCatsNoEnum catList = new MyCatsNoEnum();
        HashMap<String, MyCatNoEnum> allCats = catList.getCats();
        for (MyCatNoEnum cat : allCats.values()) {
            System.out.println(cat.toString());
        }
    }
    
    public static void printCatPollyNoEnum() {
        MyCatsNoEnum catList = new MyCatsNoEnum();
        HashMap<String, MyCatNoEnum> allCats = catList.getCats();
       
        /*Try mistyping Polly and executing the method*/
        System.out.println(allCats.get("Polly").toString());
    }
}
