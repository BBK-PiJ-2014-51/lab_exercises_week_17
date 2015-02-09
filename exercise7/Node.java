package exercise7;

public class Node {
	private Integer value;
	private Node next; 
	
	public Node(){
		value = null;
		next = null;
	}
	
	public Node(Integer value, Node node){
		this.value = value;
		next = node;
	}
	
	public void setValue(Integer value){
		this.value = value;
	}
	
	public void setNext(Node node){
		next = node;
	}
	
	public Integer getValue(){
		return value;
	}
	
	public Node getNext(){
		return next;
	}
}
