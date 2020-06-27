


public class Stack<E> {

    Node firstNode;

    // Checks if the Stack is empty
    public boolean isEmpty(){
        if(firstNode==null){
            return true;
        }
        else return false;
    }

    // Returns from the top Node, the element it holds
    public E top() throws Underflow{

        if(isEmpty()){
            throw new Underflow();
        }
               // I wonder if it is possible without a cast?
               // Because the method returns the type E owo?
        return (E) firstNode.getNodeElement();
    }

    public void push(E element){
        Node remember = firstNode;
        if(isEmpty()){
            firstNode = new Node(element, null);
        }
        else
            firstNode = new Node(element, remember);

    }

    public E pop() throws Underflow{
        if(isEmpty()){
            throw new Underflow();
        }
        else
            E element = (E) firstNode.getNodeElement();
            firstNode = firstNode.getNextNode();
            return element;
    }
    }

}
