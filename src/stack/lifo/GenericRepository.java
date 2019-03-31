package stack.lifo;

/**
 * Generic Repository
 * @author Benfarhat Elyes
 *
 * @param <T>
 */
class GenericRepository<T>{
	private GenericStack<T> stackInstance;

	public GenericRepository(GenericStack<T> stackInstance) {
		this.stackInstance = stackInstance;
	}
	@SuppressWarnings("unchecked")
	public void clear() {
		stackInstance.setStack((T[]) new Object[stackInstance.getStack().length], 0);
		stackInstance.setLength(0);
	}
	
	public boolean contains(T data) {
		for(int i = 0; i < stackInstance.size(); i++) {
			if (stackInstance.getStack()[i].equals(data))
				return true;
		}
		return false;
	}	
	
	public int count(T data) {
		int count = 0;
		for(int i = 0; i < stackInstance.size(); i++) {
			if (stackInstance.getStack()[i].equals(data))
				count++;
		}
		return count;
	}	

	public int findFirst(T data) {
		for(int i = 0; i < stackInstance.size(); i++) {
			if (stackInstance.getStack()[i].equals(data))
				return i;
		}
		return -1;
	}
	
	public int findLast(T data) {
		for(int i = stackInstance.size() -1 ; i >= 0; i--) {
			if (stackInstance.getStack()[i].equals(data))
				return i;
		}
		return -1;
	}
	
	private boolean isEmpty() {
		return (stackInstance.size() <= 0);
	}
	
	/**
	 * 
	 * @see https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
	 * @param index
	 * @return
	 */
	public boolean remove(int index) {
		System.err.println("removing index :" + index);
		if (isEmpty()
				|| index < 0
				|| index > stackInstance.size()) {
			return false;
		}
		T[] tempStack = stackInstance.getStack();
		System.arraycopy(tempStack, index + 1, tempStack, index, stackInstance.size() - index);
		
		stackInstance.setStack(tempStack, stackInstance.size() - 1);
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
		while ((i = findLast(value)) != -1) {
			found = true;
			System.err.println("--> " + i);
			remove(i);
		}
		return found;
	}
}
