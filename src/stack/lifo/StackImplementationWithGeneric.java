package stack.lifo;

public class StackImplementationWithGeneric {
	
	public static void main(String[] args) {

		GenericStack<Integer> gsi = new GenericStack<Integer>(8);
	    GenericStack<String> gss = new GenericStack<String>(3);
	    
		// Testing Stack for Integer elements
		for (int i = 1; i <= 10; i++)
			gsi.push(i);
		gsi.print();
		
		// 2 pops
		gsi.pop();
		gsi.pop();
		gsi.print();
		
		for (int i = 11; i <= 15; i++)
			gsi.push(i);	
		
		gsi.print();
	    
		gsi.pop();
	    
		gsi.push(16);
		gsi.push(17);

		gsi.print();

	    System.out.println("Size of stack is: " + gsi.size());

	    gsi.pop();
	    gsi.pop();
	    
	    gsi.print();
	    System.out.println("Top element of stack is: " + gsi.top());
	    
	    // Testing Stack for String elements
		for (int i = 65; i <= 80; i++) {
			gss.push(String.valueOf((char)i));
		}
		gss.print();
		
		// 2 pops
			gss.pop();
		gsi.pop();
		gsi.print();
		
		for (int i = 81; i <= 90; i++)
			gss.push(String.valueOf((char)i));	
		
		gss.print();
	    
		gss.pop();
	    
		gss.push(String.valueOf((char)91));
		gss.push(String.valueOf((char)92));

		gss.print();

	    System.out.println("Size of stack is: " + gss.size());

	    gss.pop();
	    gss.pop();
	    
	    gss.print();
	    System.out.println("Top element of stack is: " + gss.top());
	}

}



class GenericStack<T> {
	
	private int length;
	private T[] stackArray;
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public GenericStack(int capacity) {
		this.capacity = capacity;
		stackArray = (T[]) new Object[capacity];
	}	
	
	@SuppressWarnings("unchecked")
	public GenericStack() {
		this.capacity = 128;
		stackArray = (T[]) new Object[capacity];
	}
	
	
	public boolean isEmpty() {
		return (length == 0);
	}
	
	public int size() {
		return length;
	}
	
	public void push(T data) {
		if (length >= capacity) {
			grow();
			push(data);
		}
		stackArray[length++] = data;
	}
	
	public T pop() {
		if ((length < capacity / 4) && (capacity > 8)){
			shrink();
			return pop();
		}
		if (!isEmpty()) {
			T data = stackArray[length - 1];
			length --;
			return data;
		} else {
			return null;
		}
	}	
	
	public T top() {
		if (!isEmpty()) {
			return stackArray[length - 1];
		} else {
			return null;
		}
	}
	
	public T get(int index) {
		if (index > 0 && index < length) {
			return stackArray[index];
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public void resize(int capacity) {
		if (this.capacity == capacity) {
			return;
		}
		T[] resizedArray = (T[]) new Object[capacity];

		System.arraycopy(stackArray, 0, 
				resizedArray, 0, 
				Math.min(stackArray.length, resizedArray.length));
		this.capacity = capacity;
		stackArray = resizedArray;
	}

	public void grow() {
		resize(capacity<<1);
	}
	
	public void shrink() {
		resize(capacity>>1);
	}
	
	@SuppressWarnings("unchecked")
	public void clear() {
		length = 0;
		stackArray = (T[]) new Object[capacity];
	}
	
	public boolean contains(T data) {
		for(int i = 0; i <= length; i++) {
			if (stackArray[i].equals(data))
				return true;
		}
		return false;
	}	

	public int findFirst(T data) {
		for(int i = 0; i <= length; i++) {
			if (stackArray[i].equals(data))
				return i;
		}
		return -1;
	}
	
	public int findLast(T data) {
		for(int i = length; i >= 0; i--) {
			if (stackArray[i].equals(data))
				return i;
		}
		return -1;
	}
	
	/**
	 * 
	 * @see https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
	 * @param index
	 * @return
	 */
	public boolean remove(int index) {
		if (isEmpty()
				|| index < 0
				|| index > length) {
			return false;
		}
		System.arraycopy(stackArray, index + 1, stackArray, index, length - index);
		length--;
		return true;
	}

	public boolean removeFirst(T value) {
		if (isEmpty()) {
			return false;
		}
		return remove(findFirst(value));
	}

	public boolean removeLast(T value) {
		if (isEmpty()) {
			return false;
		}
		return remove(findLast(value));
	}
	
	public boolean removeAll(T value) {
		int i;
		boolean found = false;
		while ((i = findFirst(value)) != -1) {
			found = true;
			remove(i);
		}
		return found;
	}
	
	public String toString() {
		StringBuilder result =  new StringBuilder();
		for (int i = size() - 1; i >= 0; i--) {
			result.append(stackArray[i]).append("\n");
		}
		return result.toString();
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
