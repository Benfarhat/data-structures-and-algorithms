package stack.lifo;

public class StackWithLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
