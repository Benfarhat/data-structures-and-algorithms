package stack.lifo;

public class StackWithDynamicSizeArray {
	public static void main(String[] args) {
		implStackWithDynamicSizeArray myStack = new implStackWithDynamicSizeArray(2);
        myStack.push(3);
        System.out.println(myStack);
        myStack.push(6);
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);
        myStack.push(8);
        System.out.println(myStack);
        myStack.push(7);
        System.out.println(myStack);
        myStack.push(3);
        System.out.println(myStack);
        myStack.push(2);
        System.out.println(myStack);
        myStack.push(5);
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);
        myStack.push(0);
        System.out.println(myStack);
        myStack.push(0);
        System.out.println(myStack);
        myStack.push(0);
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);

	}

}

class implStackWithDynamicSizeArray {
	protected int capacity;
	protected int[] stackArray;
	protected int top = 0; // supposed to be the size (how many element in the stack)
	
	public implStackWithDynamicSizeArray(int capacity) {
		this.capacity = capacity;
		stackArray = new int[capacity];
		top = 0;
	}
	
	public int size() {
		return top;
	}
	
	public boolean isEmpty() {
		return (size() <=0 );
	}
	
	public boolean isFull() {
		return (size() >= capacity);
	}
	
	public void resize(int capacity) {
		if (this.capacity == capacity) {
			return;
		}
		int[] resizedArray = new int[capacity];

		System.arraycopy(stackArray, 0, 
				resizedArray, 0, 
				Math.min(stackArray.length, resizedArray.length));
		this.capacity = capacity;
		stackArray = resizedArray;
	}
	
	public void push(int item) {
		if (!isFull()) {
			stackArray[top++] = item;
		} else {
			resize(capacity * 2);
			push(item);
		}
		
	}
	
	public int pop() {
		if ((top < capacity / 4) && (capacity > 8)){
			resize(capacity / 2);
			return pop();
		}
		if (!isEmpty()) {
			int item = stackArray[top - 1];
			top--;
			return item;
		} else {
			System.out.println("Stack is empty");
			return -1;
		}
	}
	
	public int top() {
		if (!isEmpty()) {
			return stackArray[top];
		} else {
			System.out.println("Stack is empty");
			return -1;
		}
	}
	
	public void eraseAll() {
		top = 0;
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("[" + capacity + "][");
		for (int i = 0; i < top; i++)
			content.append(" " + stackArray[i]);
		content.append(" ]");
		return content.toString();
	}
}
