package stack.lifo;

import java.util.EmptyStackException;

public class StackWithLinkedList {

	public static void main(String[] args) {
		ImplStackWithLinkedList stack = new ImplStackWithLinkedList();
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

class ImplStackWithLinkedList {
	private int length;
	private ListNode top;
	
	public ImplStackWithLinkedList() {
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
		ListNode item = new ListNode(data);
		if (!isEmpty()) {
			top.setNext(item);
			item.setPrev(top);
		} 
		
		top = item;
		length++;
	}

	public int pop() {
		if (isEmpty())
			throw new EmptyStackException();
		int result = top.getData();
		top = top.getPrev();
		top.setNext(null);
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
		ListNode current = top;
		while (current != null) {
			result.append(current.toString() + "\n");

			current = current.getPrev();
		}
		return result.toString();
	}
	
	public void print() {
		System.out.println("\n" + toString() + "\n");
	}
}

class ListNode {
	public ListNode next;
	public ListNode prev;
	public int data;

	public ListNode() {
		next = null;
		prev = null;
		data = Integer.MIN_VALUE;
	}
	
	public ListNode(int data) {
		next = null;
		prev = null;
		this.data = data;
	}
	
	public ListNode getNext() {
		return next;
	}	
	
	public ListNode getPrev() {
		return prev;
	}

	public void setNext(ListNode node) {
		next = node;
	}
	
	public void setPrev(ListNode node) {
		prev = node;
	}

	public boolean hasNext() {
		return (next != null);
	}
	
	public boolean hasPrev() {
		return (prev != null);
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
