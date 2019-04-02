package linked.list;

public class GenericListNode<T> {
	private GenericListNode<T> next;
	private T data;
	public GenericListNode(T data) {
		next = null;
		this.data = data;
	}
	public GenericListNode() {
		this(null);
	}
	public GenericListNode<T> getNext() {
		return next;
	}
	public void setNext(GenericListNode<T> next) {
		this.next = next;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String toString() {
		return String.valueOf(data);
	}
}
