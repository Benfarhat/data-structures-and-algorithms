package stack.lifo;

import java.util.EmptyStackException;

public class StackWithUnidirectionalLinkedDate {

	public static void main(String[] args) {
		ImplStackWithUnidirectionalLinkedList stack = new ImplStackWithUnidirectionalLinkedList();
		for (int i = 1; i <= 10; i++)
			stack.push(i);
		stack.print();
		
		// 2 pops
		stack.pop();
		stack.pop();
		stack.print();
		
		for (int i = 11; i <= 15; i++)
			stack.push(i);	
		
        stack.print();
        
        stack.pop();
        
        stack.push(16);
        stack.push(17);

        stack.print();
  
        System.out.println("Size of stack is: " + stack.size());

        stack.pop();
        stack.pop();
        
        stack.print();
        System.out.println("Top element of stack is: " + stack.top());

	}

}

class ImplStackWithUnidirectionalLinkedList {
	private int length;
	private UDListNode top;
	
	public ImplStackWithUnidirectionalLinkedList() {
		length = 0;
		top = null;
	}
	
	public boolean isEmpty() {
		return (length == 0);
	}
	
	public int size() {
		return length;
	}
	
	public void push(int data) {
		UDListNode item = new UDListNode(data);
		if (!isEmpty()) {
			item.setNext(top);
		} 
		
		top = item;
		length++;
	}

	public int pop() {
		if (isEmpty())
			throw new EmptyStackException();
		int result = top.getData();
		top = top.getNext();
		length--;
		return result;
	}
	
	public int top() {
		if (isEmpty())
			throw new EmptyStackException();

		return top.getData();
	}
	
	public String toString() {
		StringBuilder result =  new StringBuilder();
		UDListNode current = top;
		while (current != null) {
			result.append(current.toString() + "\n");

			current = current.getNext();
		}
		return result.toString();
	}
	
	public void print() {
		System.out.println("\n" + toString() + "\n");
	}
}
// Unidirectional (UD) only getNext and setNext are available
class UDListNode {
	public UDListNode next;
	public int data;

	public UDListNode() {
		next = null;
		data = Integer.MIN_VALUE;
	}
	
	public UDListNode(int data) {
		next = null;
		this.data = data;
	}
	
	public UDListNode getNext() {
		return next;
	}	
	
	public void setNext(UDListNode node) {
		next = node;
	}
	
	public boolean hasNext() {
		return (next != null);
	}
	
	public int getData() {
		return data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public String toString() {
		return Integer.toString(data);
	}
}
