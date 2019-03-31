package stack.lifo;

public class StackImplementationWithGeneric {
	
	public static void main(String[] args) {

		GenericStack<Integer> gsi = new GenericStack<Integer>(4);
	    GenericStack<String> gss = new GenericStack<String>(3);
	    
	    GenericStack<String> testGSS = new GenericStack<String >(1);
	    
	    
		// Testing Stack for Integer elements
		for (int i = 1; i <= 10; i++)
			gsi.push(i);
		gsi.print();
		gsi.pop(); gsi.pop();
		gsi.print();
		for (int i = 11; i <= 15; i++)
			gsi.push(i);	
		gsi.print();
		gsi.pop(); gsi.push(16); gsi.push(17); gsi.print();
	    System.out.println("Size of stack is: " + gsi.size());
	    gsi.pop(); gsi.pop(); gsi.print();
	    System.out.println("Top element of stack is: " + gsi.top());
	    
	    // Testing Stack for String elements
		for (int i = 65; i <= 80; i++) {
			gss.push(String.valueOf((char)i));
		}
		gss.print();
		gss.pop(); gsi.pop(); gsi.print();
		for (int i = 81; i <= 90; i++)
			gss.push(String.valueOf((char)i));
		gss.print();
		gss.pop(); gss.push(String.valueOf((char)91)); gss.push(String.valueOf((char)92)); gss.print();
		System.out.println("Size of stack is: " + gss.size());
	    gss.pop(); gss.pop(); gss.print();
	    System.out.println("Top element of stack is: " + gss.top());
	    
	    // Testing GS methods
	    testGSS.push("a");
	    testGSS.push("b");
	    testGSS.push("a");
	    testGSS.push("c");
	    testGSS.push("d");
	    testGSS.push("A");
	    testGSS.push("c");
	    testGSS.push("a");
	    testGSS.print();

	    GenericRepository<String> repository = new GenericRepository<String>(testGSS);
	    
	    System.out.println("number of letter a occurence : " + repository.count("a"));
	    System.out.println("Did this stack contain the letter c: " + repository.contains("c"));
	    System.out.println("first position of letter c is: " + repository.findFirst("c"));
	    System.out.println("last position of letter c is: " + repository.findLast("c"));
	    testGSS.push("a");testGSS.push("a");testGSS.push("a");
	    System.out.println("number of letter a occurence : " + repository.count("a"));

	    System.out.println("removing first position of letter c ");
	    repository.removeFirst("c"); testGSS.print();

	    System.out.println("removing all occurence of letter a");
	    repository.removeAll("a"); 
	    System.out.println("Our final stack: ");
	    testGSS.print();

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
		if (length >= capacity || length == capacity - 1) {
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
	
	public T[] getStack() {
		return stackArray;
	}
	
	public void setLength(int length) {
		this.length = length;
	}	
	
	public void setStack(T[] stackArray, int length) {
		this.stackArray = stackArray;
		this.length = length;
	}
	
}
