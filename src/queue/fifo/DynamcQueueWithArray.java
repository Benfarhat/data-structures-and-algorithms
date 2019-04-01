package queue.fifo;

public class DynamcQueueWithArray {
	public static void main(String[] args) {
		DynamcQueueWithArrayImpl<String> queue = new DynamcQueueWithArrayImpl<String>(8);

		queue.enQueue("A");
		queue.enQueue("B");
		queue.enQueue("C");
		queue.enQueue("D");
		queue.enQueue("E");
		System.out.println(queue.info());
		queue.deQueue();
		System.out.println(queue.info());
		queue.enQueue("F");
		System.out.println(queue.info());
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		System.out.println(queue.info());
		queue.enQueue("G");
		System.out.println(queue.info());
		queue.enQueue("H");
		System.out.println(queue.info());
		queue.enQueue("I");
		System.out.println(queue.info());
		
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
	
	public void compact() {

		if (isEmpty() || front == 0) {
			return;
		}
		
		T[] tempStack = queue;
		System.arraycopy(tempStack, front, queue, 0, rear - front + 1);

	}
	public void enQueue(T item) {
		if (isFull()) {
			/*
			if (size < capacity / 2) {
				compact();
			} else {
				increaseCapacity();
			}
			*/
			increaseCapacity();
			System.out.println("overflow");
		}
		
		rear++;
		
		queue[rear] = item;
		size++;
	}	
	
	public T deQueue() {
		if (isEmpty()) {
			System.out.println("underflow");
			return null;
		}
		front++;
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
		int newCapacity = queueSize()<<1;
		/*
		T[] resizedArray = (T[]) new Object[newCapacity];

		
		System.arraycopy(stackArray, 0, 
				resizedArray, 0, 
				Math.min(stackArray.length, resizedArray.length));
		this.capacity = capacity;
		stackArray = resizedArray;*/
	}
	
	public void decreaseCapacity() {
		
	}


	public String info() {
		StringBuilder toString = new StringBuilder();
		toString.append("\n=========:-------");
		toString.append("\nsize     : " + size);
		toString.append("\ncapacity : " + capacity);
		toString.append("\nfront    : " + front);
		toString.append("\nrear     : " + rear);
		toString.append("\ncontent  : " + toString());
		toString.append("\n=========:-------\n");
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