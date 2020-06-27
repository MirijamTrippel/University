package stack;

public interface Stack<E> {

	public boolean isEmpty();

	public E top() throws Underflow;

	public void push(E element) throws Overflow;

	public E pop() throws Underflow;

	//public E top() ;

	//public void push(E element);

	//public E pop() ;

}
