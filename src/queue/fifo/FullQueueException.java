package queue.fifo;

class FullQueueException extends Exception {
	private static final long serialVersionUID = 1L;

	public FullQueueException(String message) {
        super(message);
    }
}