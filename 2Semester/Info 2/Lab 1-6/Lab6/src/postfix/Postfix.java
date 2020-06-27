package postfix;

import stack.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Postfix {


    public static String infixToPostfix(String infix) throws Exception {
        // TODO Auto-generated method stub
        //1. Given a sequence of tokens(characters) and a result(postfix)
        LinkedListStack<Character> stack = new LinkedListStack<Character>();
        char[] characters = infix.replaceAll(" ", "").toCharArray();
        String postfix = "";
        try {
            if (Character.isDigit(characters[characters.length - 1])) {
                //2. loop through array
                for (char t : characters) {
                    //2.2 if t is an operand
                    if (Character.isDigit(t)) {
                        postfix += t;
                    }
                    //2.3 If t is an open parenthesis, push it
                    else if (t == '(') {
                        stack.push(t);
                    }
                    //2.5 If t is an operator
                    else if (t == '+' || t == '-' || t == '*' || t == '/' || t == '^') {
                        if (stack.isEmpty()) {
                            stack.push(t);
                        } else {
                            //2.5.1
                            while (!stack.isEmpty() && (!((precedence((char) stack.top()) < precedence(t)) ||
                                    (t == '^' && precedence((char) stack.top()) == precedence(t))))) {
                                postfix += stack.top();
                                stack.pop();
                            }
                            //2.5.2
                            stack.push(t);
                        }
                        //2.4 if t is a close parenthesis
                    } else if (t == ')') {
                        //2.4.1
                        while (!stack.isEmpty() && stack.top() != '(') {
                            postfix += stack.top();
                            stack.pop();
                        }
                        //2.4.2
                        stack.pop();
                    }
                }
            }

            //3. while stack not empty
            while (!stack.isEmpty()) {
                postfix += stack.top();
                stack.pop();
            }
            //4. Return result
            return postfix;
        } catch (Exception E) {
            throw new Exception("please enter your calculation in infix format.");
        }
    }


    public static int precedence(char ch) {
        switch (ch) {
            case '^':
            case '/':
            case '*':
                return 1;

            case '+':
            case '-':
                return 0;

            default:
                return -1;
        }
    }

    public static double evaluate(String postfix) throws Underflow,Overflow {

        LinkedListStack<Double> stack = new LinkedListStack<>();

        for (int n = 0; n < postfix.length(); n++) {
			Character ch = postfix.charAt(n);

			if ('0' <= ch && ch <= '9')
				stack.push(Double.parseDouble(ch.toString()));
			else {
				//operator
				switch (ch) {

					case '+':
						double b = 0;

						b = stack.pop();
						double a = stack.pop();
						double result = a + b;
						stack.push(result);

						break;

					case '-':
						b = stack.pop();
						a = stack.pop();
						result = a - b;
						stack.push(result);
						break;
					case '*':
						b = stack.pop();
						a = stack.pop();
						result = a * b;
						stack.push(result);
						break;
					case '/':
						b = stack.pop();
						a = stack.pop();
						result = a / b;
						stack.push(result);
						break;
					case '^':
						b = stack.pop();
						a = stack.pop();
						result = Math.pow(a, b);
						stack.push(result);

						break;

				}

			}
		}


        return stack.pop();

}

    public static void calculateResultInfix() throws IOException, Exception {
        System.out.println("please enter Infix String:");
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println("Converted Postfix String : " + infixToPostfix(input));
        System.out.println("Calculated Result : " + evaluate(infixToPostfix(input)));

    }
}
