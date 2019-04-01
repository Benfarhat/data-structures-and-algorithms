package queue.fifo;

import java.util.Random;
import java.util.stream.IntStream;

public class DynamicSimpleQueueWithArray {
	public static void main(String[] args) {
		DynamicSimpleQueueWithArrayImpl<String> queue = new DynamicSimpleQueueWithArrayImpl<String>(8);
		System.out.println(queue.info("STARTING"));
		
		new Random().ints(100,65,90).parallel().forEach(letter -> queue.enQueue(String.valueOf((char) (letter))));
		System.out.println(queue.info("AFTER 100 ENQUEUE"));
		
		IntStream.rangeClosed(1, 82).parallel().forEach(i -> queue.deQueue());
		System.out.println(queue.info("AFTER 83 DEQUEUE"));
	
	}
}

class DynamicSimpleQueueWithArrayImpl<T> {
	private T[] queue;
	private int capacity, front, rear, size;
	private static final int LOWER_CAPACITY = 8;
	private static final boolean MORE_INFO = false;

	@SuppressWarnings("unchecked")
	public DynamicSimpleQueueWithArrayImpl(int capacity) {
		queue = (T[]) new Object[capacity];
		this.capacity = capacity;
		front = 0;
		rear = -1;
		size = 0;
	}
	
	public DynamicSimpleQueueWithArrayImpl() {
		this(LOWER_CAPACITY);
		if (MORE_INFO) 
			System.out.println("Using default capacity value.");
	}
	
	public synchronized void compact() {
		if (isEmpty() || front == 0) {
			return;
		}
		double start = System.nanoTime();
		T[] tempStack = queue;
		System.arraycopy(tempStack, front, queue, 0, rear - front+1);
		rear = rear - front;
		front = 0;
		rear = rear - front;
		double stop = System.nanoTime();
		if (MORE_INFO) 
			System.out.println("Compact done in " + ((stop - start) / 1000000) + " milliseconds.");

	}
	public synchronized void enQueue(T item) {
		if (isOnTheLimit()) {
			if (size < capacity / 2) {
				compact();
			} else {
				increaseCapacity();
			}
			if (MORE_INFO) 
				System.err.println("overflow");
		}
		
		
		
		rear++;
		queue[rear] = item;
		
		size++;
	}	
	
	public synchronized T deQueue() {
		if (isEmpty()) {
			if (MORE_INFO) 
				System.err.println("underflow");
			return null;
		} 
		
		if ((size < capacity / 4) && (capacity > LOWER_CAPACITY)) {
			decreaseCapacity();
		}
		
		front++;
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
		return size == capacity;
	}
	
	public boolean isOnTheLimit() {
		return rear == (capacity - 1);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void increaseCapacity() {
		double start = System.nanoTime();
		int newCapacity = queueSize()<<1;
		
		T[] resizedArrayQueue = (T[]) new Object[newCapacity];

		System.arraycopy(queue, 0, 
				resizedArrayQueue, 0, 
				size);
		this.capacity = newCapacity;
		queue = resizedArrayQueue;
		double stop = System.nanoTime();
		if (MORE_INFO) 
			System.out.println("Capacity increased in " + ((stop - start) / 1000000) + " milliseconds.");
		compact();
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void decreaseCapacity() {
		compact();
		double start = System.nanoTime();
		int newCapacity = Math.max(queueSize()>>1, LOWER_CAPACITY);
		
		
		T[] resizedArrayQueue = (T[]) new Object[newCapacity];

		System.arraycopy(queue, 0, 
				resizedArrayQueue, 0, 
				size);

		this.capacity = newCapacity;
		queue = resizedArrayQueue;
		double stop = System.nanoTime();
		if (MORE_INFO) 
			System.out.println("Capacity decreased in " + ((stop - start) / 1000000) + " milliseconds.");
		
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