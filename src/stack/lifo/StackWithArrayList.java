package stack.lifo;

import java.util.ArrayList;
import java.util.List;

public class StackWithArrayList {
	
	public static void main(String[] args) {
		ImplStackWithArrayList stack = new ImplStackWithArrayList();
		for (int i = 1; i <= 10; i++)
			stack.push(i);
		stack.print();
		
		// 2 pops
		stack.pop();
		stack.pop();
		stack.print();
		
		for (int i = 11; i <= 15; i++)
			stack.push(i);	
		
        stack.print();
        
        stack.pop();
        
        stack.push(16);
        stack.push(17);

        stack.print();
  
        System.out.println("Size of stack is: " + stack.size());

        stack.pop();
        stack.pop();
        
        stack.print();
        System.out.println("Top element of stack is: " + stack.top());
	}
}

class ImplStackWithArrayList {
	
	List<Integer> stackList;
	
	public ImplStackWithArrayList() {
		stackList = new ArrayList<>();
	}
	
	public boolean isEmpty() {
		return(stackList.isEmpty());
	}
	
	public int size() {
		return stackList.size();
	}
	
	public void push(int data) {
		stackList.add(data);
	}

	public int pop() {
		if (!isEmpty()) {
			int result = stackList.get(size() -1);
			stackList.remove(size() -1);
			return result;
		} else {
			System.out.println("Stack is empty.");
			return -1;
		}
	}

	public int top() {
		if (!isEmpty()) {
			return stackList.get(size() -1);
		} else {
			System.out.println("Stack is empty.");
			return -1;
		}
	}
	
	public int peek() {
		if (!isEmpty()) {
			return stackList.get(size() -1);
		} else {
			System.out.println("Stack is empty.");
			return -1;
		}
	}
	
	public String toString() {
		StringBuilder result =  new StringBuilder();
		for (int i = (size() - 1); i >= 0; i--) {
			result.append(String.valueOf(stackList.get(i)) + "\n");
		}
		return result.toString();
	}
	
	public void print() {
		System.out.println(toString());
	}
}
