package postfix;


public class Main {


    public static void main(String[] args) throws Exception {

        double result = Postfix.evaluate("223*+");
        String posFix = Postfix.infixToPostfix("2+2^3");
        Postfix.calculateResultInfix();

    }
}
