package Stack;

import Graph.Vertex;

public class Stack {

	private Vertex[] stack;
	private int size;
	
	public Stack() {
		stack = new Vertex[10];
		size = -1;
	}
	
	public Stack(int sizeArray) {
		stack = new Vertex[sizeArray];
		size = -1;;
	}
	
	public void push(Vertex vertex) {
		stack[++size] = vertex;
	}
	
	public Vertex pop() {
		return stack[size--];
	}
	
	public Vertex peek() {
		return stack[size];
	}
	
	public boolean isEmpty() {
		return size == -1;
	}
	
	public int size() {
		return size + 1;
	}
}
