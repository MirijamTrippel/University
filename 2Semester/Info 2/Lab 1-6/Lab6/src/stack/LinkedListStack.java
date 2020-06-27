package stack;

public class LinkedListStack<E> implements Stack<E> {

    private Node<E> top = null;


	private class Node<E> {
		E data; //  data
		private Node<E> link = null;

		Node (E element){
			data = element;
		}
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public  E top() throws Underflow {

		if (top == null) throw new Underflow("Stack Underflow");
		return top.data;
	}

	@Override
	public void push(E element) throws Overflow{

		Node<E> newItem = new Node<E>(element);
		if (newItem == null)throw new Overflow("Stack Overflow");

		if (top == null) {
			top = newItem;
		} else {
			// New Top
			newItem.link = top;
			top = newItem;
		}
	}

	@Override
	public E pop() throws Underflow {

		if (top == null) throw new Underflow("Stack Underflow");
		E output = top.data;
		top = top.link;
		return output;
	}


}
