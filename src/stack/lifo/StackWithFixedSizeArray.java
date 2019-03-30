package stack.lifo;

public class StackWithFixedSizeArray {
	public static void main(String[] args) throws Exception {
		Implementation myStack = new Implementation(5);
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
class Implementation {

	protected int capacity;
	protected int[] stackArray;
	protected int top; // more see as size than index
	
	public Implementation(int capacity) {
		this.capacity = capacity;
		stackArray = new int[capacity];
		top = 0;
	}
	
	public int size() {
		return top;
	}
	
	public boolean isEmpty() {
		return (top <= 0);
	}
	
	public void push(int data) throws Exception {
		if (size() >= capacity)
			throw new Exception("Stack is full" + size() + " - " + capacity);
		stackArray[top++] = data;
	}
	
	public int pop() throws Exception {
		if (isEmpty())
			throw new Exception("Stack is empty");
		int data = stackArray[top];
		stackArray[top] = Integer.MIN_VALUE;
		top--;
		return data;
	}
	
	public int top() throws Exception {
		if (isEmpty())
			throw new Exception("Stack is empty");
		return stackArray[top];
		
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("[");
		for (int i = 0; i < top; i++)
			content.append(" " + stackArray[i]);
		content.append(" ]");
		return content.toString();
	}
}
