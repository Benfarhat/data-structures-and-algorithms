package queue.fifo;

import java.util.Random;
import java.util.stream.IntStream;

public class DynamcQueueWithArray {
	public static void main(String[] args) {
		DynamcQueueWithArrayImpl<String> queue = new DynamcQueueWithArrayImpl<String>(8);
		System.out.println("You should see this queue like a sort of \"array shift\".\""
				+ "it's like moving 4 consecutive cars on a road and to keep going, we built more road every time we reach the end");
		System.out.println(queue.info("STARTING")); // front should be zero and rear -1
		
		new Random().ints(107,65,90).parallel().forEach(letter -> queue.enQueue(String.valueOf((char) (letter))));
		System.out.println(queue.info("AFTER 101 ENQUEUE"));	// rear should be 106 (107 -1)
		
		new Random().ints(32,65,90).parallel().forEach(letter -> queue.enQueue(String.valueOf((char) (letter))));
		System.out.println(queue.info("AFTER 32 ENQUEUE")); // rear should be 138 (32 + 106)
		
		IntStream.rangeClosed(1, 1).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER ONE DEQUEUE")); // front should be 1

		IntStream.rangeClosed(1, 10).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER 10 DEQUEUE")); // front should be 11 (10 + 1)

		IntStream.rangeClosed(1, 100).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER 100 DEQUEUE")); // front should be 111, here size is 28, 138 - 111 (closed) mean 138 - 111 + 1
		
		IntStream.rangeClosed(1, 27).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER 27 DEQUEUE")); // front should be 138, size 1, mean the element in the 138th position is the only one in the queue	

		IntStream.rangeClosed(1, 18).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER 18 DEQUEUE")); // front should be 139, over this position queue (or pseudo circular queue and array shift) is empty

		new Random().ints(20,65,90).parallel().forEach(letter -> queue.enQueue(String.valueOf((char) (letter))));
		System.out.println(queue.info("AFTER 20 ENQUEUE")); // rear should be 158 and size 20
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
		}
		rear = (++rear) % (queueSize() + 0);
		queue[rear] = item;
		size++;
	}	
	
	public synchronized T deQueue() {
		if (isEmpty()) {
			System.out.println("underflow");
			return null;
		}
		
		front = (++front) % (queueSize() - 1);

		
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