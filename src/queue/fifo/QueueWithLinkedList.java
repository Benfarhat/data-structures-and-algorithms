package queue.fifo;

import java.util.Random;
import java.util.stream.IntStream;

public class QueueWithLinkedList {
	public static void main(String[] args) {
		QueueWithLinkedListImpl<String> queue = new QueueWithLinkedListImpl<>();
		
		new Random().ints(20, 65, 90).forEach(letter -> queue.enQueue(String.valueOf((char) letter)));		
		System.out.println(queue);
		
		IntStream.rangeClosed(1, 16).forEach(i -> queue.deQueue());
		System.out.println(queue);
		
		new Random().ints(5, 65, 90).forEach(letter -> queue.enQueue(String.valueOf((char) letter)));		
		System.out.println(queue);
		
		IntStream.rangeClosed(1, 10).forEach(i -> queue.deQueue());
		System.out.println(queue);
	}
}

class ListNode<T> {
	public ListNode<T> next;
	public T data;
	public ListNode() {
		this(null);
	}
	public ListNode(T data) {
		next = null;
		this.data = data;
	}
	@SuppressWarnings("rawtypes")
	public ListNode getNext() {
		return next;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setNext(ListNode next) {
		this.next = next;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String toString() {
		return String.valueOf(data);
	}
}
class QueueWithLinkedListImpl<T> {
	private int size;
	private ListNode<T> front, rear;
	
	public QueueWithLinkedListImpl() {
		size = 0;
		front = rear = null;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void enQueue(T data) {
		ListNode<T> node = new ListNode<T>(data);
		if (isEmpty()) {
			front = node;
		} else {
			rear.setNext(node);
		}
		rear = node;
		size++;
	}
	@SuppressWarnings("unchecked")
	public T deQueue() {
		if (isEmpty()) {
			return null;
		}
		T result = front.getData();
		front = front.getNext();
		size--;
		if (isEmpty()) {
			rear = null;
		}
		return result;
	}
	public T first() {
		if (isEmpty()) {
			return null;
		}
		return front.getData();
	}
	public int size() {
		return size;
	}
	@SuppressWarnings("unchecked")
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(FRONT-SIZE<  ");
		ListNode<T> current = front;
		while (current != null) {
			result.append(current.getData());
			current = current.getNext();
			if (current != null) {
				result.append(", ");
			}
		}
		result.append("  <REAR-SIDE)");
		return result.toString();
	}
}
