package queue.fifo;

import java.util.Random;
import java.util.stream.IntStream;

public class DynamcQueueWithArray {
	public static void main(String[] args) {
		DynamcQueueWithArrayImpl<String> queue = new DynamcQueueWithArrayImpl<String>(8);
		System.out.println(queue.info("STARTING"));
		
		new Random().ints(100,65,90).parallel().forEach(letter -> queue.enQueue(String.valueOf((char) (letter))));
		System.out.println(queue.info("AFTER 100 ENQUEUE"));
		
		IntStream.rangeClosed(1, 82).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER 82 DEQUEUE"));
		
	}
}

class DynamcQueueWithArrayImpl<T> {
	private T[] queue;
	private int capacity, front, rear, size;
	private static final int DEFAULT_CAPACITY = 8;

	@SuppressWarnings("unchecked")
	public DynamcQueueWithArrayImpl(int capacity) {
		queue = (T[]) new Object[capacity];
		this.capacity = capacity;
		front = 0;
		rear = -1;
		size = 0;
	}
	
	public DynamcQueueWithArrayImpl() {
		this(DEFAULT_CAPACITY);
		System.out.println("Using default capacity value.");
	}
	
	public synchronized void enQueue(T item) {
		if (isFull()) {
			increaseCapacity();
			System.out.println("overflow");
		}
		
		rear++;
		
		if (rear >= queueSize() && size != queueSize()) {
			rear = 0;
		}
		queue[rear] = item;
		size++;
	}	
	
	public synchronized T deQueue() {
		if (isEmpty()) {
			System.out.println("underflow");
			return null;
		}
		front++;
		if (front > (queueSize() - 1)) {
			front = 0;
		} else {
			
		}
		size--;
		return queue[front];
		
	}

	public int size() {
		return size;
	}
	
	public int queueSize() {
		return queue.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == queueSize();
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void increaseCapacity() {
		capacity = queueSize()<<1;
		T[] resizedArray = (T[]) new Object[capacity];

		int tmpFront = front;
		int index = -1;
		while (true) {
			resizedArray[++index] = queue[tmpFront++];
			if (tmpFront == queueSize()) {
				tmpFront = 0;
			}
			
			if (size == (index + 1)) {
				break;
			}
		}
		queue = resizedArray;
		front = 0;
		rear = index;
	}
	
	public String info(String msg) {
		StringBuilder toString = new StringBuilder();
		toString.append("\n~^~^~^~^~^~^~^~^~^~^~^~^[" + msg + "]^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~");
		toString.append("\n| size     | " + size); 
		toString.append("\n| capacity | " + capacity);
		toString.append("\n| front    | " + front);
		toString.append("\n| rear     | " + rear);
		toString.append("\n| content  | " + toString());
		toString.append("\n~^~^~^~^~^~^");
		return toString.toString();
	}
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("(");
		for (int i = rear; i >= front; i--) {
			toString.append(i + ":" + queue[i]);
			if (i > front) {
				toString.append(", ");
			}
		}
		toString.append(")");
		return toString.toString();
	}
}