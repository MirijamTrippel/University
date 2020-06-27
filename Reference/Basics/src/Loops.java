import java.util.ArrayList;

public class Loops {

    public static void main(String[] args) {
        // write your code here
        loopFor(70,5,100);
    }


    public void Loops(String[] args) {
    // write your code here

}




    public static int loopFor(int base, int times,int by){
        int result = base;
        for ( int i = 0 ; i <= times ; i++){
            result = result + by ;
        }
        System.out.println(result - by);
        return result - by;
    }



}
