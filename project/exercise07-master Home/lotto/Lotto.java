import java.util.Set;
import java.util.HashSet;
import java.util.Random;

/**
 * Draw and count Lotto Numbers
 * 
 * @author Barne Kleinen 
 */
public class Lotto
{
    /*Form of creating objects:
    object type(class name), object name(xXx), object value(initilisation, constructor)
    
    objects are allways classes
    constructor has the name of the class
    
    new Lotto () {
    
    
    }
    
    we need to create a collection
    a method that generates a random number and puts the result in the collection
    
    
   
     
    
    */
    
    Set<String> set = new HashSet();
    Random roll = new Random();
    
    
    public void test(){
        System.out.println("cat" + 3 + 8 + 9 + "fish");
    }
    
    
    public Set<Integer> drawLotto(){
        return null;
    }

    public int lottoDraw(){
        int lotto = 49;
        int result = roll.nextInt(lotto);
        return result+1;
    }
    
    
    public int[] countNumbers(int i){
        
        
        int[] frequencyTable = new int[49];

        return frequencyTable;
    }

    public void generateAndPrintFrequencyTable(int n){
    }
}
