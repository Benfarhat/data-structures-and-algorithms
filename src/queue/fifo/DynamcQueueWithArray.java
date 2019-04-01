package queue.fifo;

public class DynamcQueueWithArray {
	public static void main(String[] args) {
		
	}
}

class DynamcQueueWithArrayImpl<T> {
	private T[] queue;
	private int capacity, front, rear, size;
	private static final int DEFAULT_CAPACITY = 8;

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
	
	public void enQueue(T item) {
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
	
	public T deQueue() {
		if (isEmpty()) {
			System.out.println("underflow");
			return null;
		}
		
		rear++;
		if (rear >= queueSize() && size != queueSize()) {
			rear = 0;
		} else {
			front++;
			if (front > queueSize() - 1) {
				front = 0;
			}
			size--;
		} 
		return queue[front];
		
	}

	public int size() {
		return size;
	}
	
	public int queueSize() {
		return queue.length;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public void increaseCapacity() {
		
	}
	
	public void decreaseCapacity() {
		
	}
}