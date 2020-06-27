


public class Node<E> {

    private Node nextNode = null;
    private E element;

    public Node(E element, Node nextNode){
        this.element = element;
        this.nextNode = nextNode;

    }

    public Node getNextNode(){
        return nextNode;
    }

    public E getNodeElement(){
        return element;
    }

    public boolean hasNextNode(){
        if(nextNode != null){
            return true;
        }
        else
            return false;
    }





}
