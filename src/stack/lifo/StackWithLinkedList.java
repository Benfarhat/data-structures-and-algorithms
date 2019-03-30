package stack.lifo;

public class StackWithLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class ImplStackWithLinkedList {
	private int length;
	private ListNode topOfStack;
	
	public ImplStackWithLinkedList() {
		length = 0;
		topOfStack = null;
	}
	
	public boolean isEmpty() {
		return (length == 0);
	}
	
	public int size() {
		return length;
	}
	
	public void push(int data) {
		ListNode item = new ListNode(data);
		item.setNext(topOfStack);
		topOfStack = item;
		length++;
	}
	
	public int pop() {
		if (isEmpty)
			throw new EmptyStackException;
	}
}

class ListNode {
	public ListNode next;
	public int data;

	public ListNode() {
		next = null;
		data = Integer.MIN_VALUE;
	}
	
	public ListNode(int data) {
		next = null;
		this.data = data;
	}
	
	public ListNode getNext() {
		return next;
	}
	
	public void setNext(ListNode node) {
		next = node;
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
