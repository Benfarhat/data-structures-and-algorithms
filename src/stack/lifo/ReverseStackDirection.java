package stack.lifo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseStackDirection {
	public static void main(String[] args) {
		ReverseDirectionStackImpl<String> stack = new ReverseDirectionStackImpl<>();
		String citation = "If you fall asleep now, you will dream. If you study now, you will live your dream.";
		Arrays
			.asList(citation.split("\\s+"))
			.forEach(stack::push);

		stack.clear();
		Arrays
			.asList("People who invest in the future are realists.".split("\\s+"))
			.forEach(stack::push);
		System.out.println("POP operations in normal direction");
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " >> ");
		}
		System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
		
		stack.clear();
		Arrays
			.asList("People who invest in the future are realists.".split("\\s+"))
			.forEach(stack::push);
		
		System.out.println("Stack : " + stack);
		stack.changeDirection();

		System.out.println("Stack after changing direction : " + stack);
		
		System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");

		System.out.println("POP operations in inversed direction");
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " >> ");
		}
		
		
	}

}

class ReverseDirectionStackImpl<T> {
	
	private List<T> stackList;
	boolean top = true;
	
	public ReverseDirectionStackImpl() {
		stackList = new ArrayList<T>();
		top = true;
	}

	public void changeDirection() {
		top = !top;
		
		if (top) {
			System.out.println("Pushing and poping from top...");
		} else {
			System.out.println("Pushing and poping from bottom...");
		} 
	}
	
	public int getTopIndex() {
		if (top) {
			return (stackList.size() - 1);
		} else {
			return 0;
		}
	}
	
	public boolean isEmpty() {
		return stackList.isEmpty();
	}
	
	public void push(T item) {
		if (top) {
			stackList.add(item);
		} else {
			List<T> tempArrayList = stackList;
			stackList.clear();
			stackList.add(item);
			stackList.addAll(tempArrayList);
		}
		
	}
	
	public T pop() {
		T item = stackList.get(getTopIndex());
		stackList.remove(getTopIndex());
		return item;
	}
	
	public T top() {
		return stackList.get(getTopIndex());
	}
	
	public T peek() {
		return top();
	}
	
	public void reverse() {
		List<T> reversedStack = new ArrayList<T>();
		while(!isEmpty()) {
			reversedStack.add(pop());
		}
		stackList = reversedStack;
	}
	
	public void clear() {
		stackList.clear();
	}
	
	public String toString() {
		StringBuilder toString = new StringBuilder();
		if (top) {
			for(int i = stackList.size() - 1; i >= 0; i--) {
				toString
				.append(String.valueOf(stackList.get(i)))
				.append(" > ");
			}
		} else {
			for(int i = 0; i < stackList.size() - 1 ; i++) {
				toString
				.append(String.valueOf(stackList.get(i)))
				.append(" > ");
			}
		}
		return toString.toString();
	}
}
