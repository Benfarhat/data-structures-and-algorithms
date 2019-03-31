package stack.lifo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseStack {
	public static void main(String[] args) {
		ReverseStackImpl<String> stack = new ReverseStackImpl<>();
		String citation = "If you fall asleep now, you will dream. If you study now, you will live your dream.";
		Arrays
			.asList(citation.split("\\s+"))
			.forEach(stack::push);

		System.out.println(stack);
		stack.reverse();
		System.out.println(stack);
	}

}

class ReverseStackImpl<T> {
	
	private List<T> stackList;
	
	public ReverseStackImpl() {
		stackList = new ArrayList<T>();
	}
	
	public boolean isEmpty() {
		return stackList.isEmpty();
	}
	
	public void push(T item) {
		stackList.add(item);
	}
	
	public T pop() {
		T item = stackList.get(stackList.size() - 1);
		stackList.remove(stackList.size() - 1);
		return item;
	}
	
	public T top() {
		return stackList.get(stackList.size() - 1);
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
	
	public String toString() {
		StringBuilder toString = new StringBuilder();
		for(T e: stackList) {
			toString
			.append(String.valueOf(e))
			.append(" ");
		}
		return toString.toString();
	}
}
