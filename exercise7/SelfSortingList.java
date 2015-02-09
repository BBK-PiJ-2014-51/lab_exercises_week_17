package exercise7;

public class SelfSortingList implements Runnable{
	private Node head = null;
	private boolean isSorted = true;
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(20);
				if (!isSorted) sortList();
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void sortList() {
		Node currentNode = head;
		boolean isSorted = true;
		while (currentNode != null){
			// TO DO finish impl
		}
	}

	public synchronized void add(Node node){
		if (head == null){
			head = node;
			isSorted = true;
		} else {
			isSorted = (node.getValue() < head.getValue());
			node.setNext(head);
			head = node;
		}
	}
	
	
	
	

}
