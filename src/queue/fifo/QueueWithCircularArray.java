package queue.fifo;

public class QueueWithCircularArray {
	public static void main(String[] args) throws FullQueueException, EmptyQueueException {
		QueueWithCircularArrayImpl<Integer> list = new QueueWithCircularArrayImpl<>(16);
		list.enQueue(10);
		list.enQueue(20);
		list.enQueue(30);
		list.enQueue(40);
		list.enQueue(50);
		list.enQueue(60);
		list.enQueue(70);
		list.enQueue(80);
		list.enQueue(90);
		System.out.println("Content of list: \n" + list);

		list.deQueue();
		list.deQueue();
		list.deQueue();
		list.enQueue(100);
		list.enQueue(110);
		System.out.println("Content of list after 3 deQueue and 2 enQueue operations: \n" + list);
	}

}

class QueueWithCircularArrayImpl<T>{
	private T[] queue;
	private int size, front, rear;
	private int capacity;
	
	private static final int DEFAULT_CAPACITY = 16;

	public QueueWithCircularArrayImpl() {
		this(DEFAULT_CAPACITY);
		System.out.println("Using default capacity value.");
	}
	
	@SuppressWarnings("unchecked")
	public QueueWithCircularArrayImpl(int capacity) {
		queue = (T[]) new Object[capacity];
		this.capacity = capacity;
		size = 0;
		front = 0;
		rear = 0;
	}
	
	public void enQueue(T data) throws FullQueueException {
		if (size == capacity) {
			throw new FullQueueException("Overflow: Enqueue operation not allowed, Queue is full.");
		} else {
			if (data == null) {
				throw new NullPointerException("data provided is null.");
			}
			size ++;
			queue[rear] = data;
			rear = (rear + 1) % capacity;
		}
	}
	
	public T deQueue() throws EmptyQueueException {
		if (size == 0) {
			throw new EmptyQueueException("Underflow: Dequeue operation not allowed, Queue is empty.");
		} else {
			size --;
			T data = queue[front % capacity];
			queue[front] = null;
			front = (front + 1) % capacity;
			return data;
		}
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public boolean isFull() {
		return (size == capacity);
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(");
		for(int i = 0; i < size; i++) {
			result.append(String.valueOf(queue[(front + i) % capacity]));
			if (i < size - 1) {
				result.append(", ");
			}
		}
		result.append(")");
		return result.toString();

	}
}