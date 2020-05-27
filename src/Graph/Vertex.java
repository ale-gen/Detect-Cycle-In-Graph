package Graph;

public class Vertex {
	
	public int key;
	public boolean visited;
	public int source;
	public int destination;
	
	public Vertex() {
		
	}
	
	public Vertex(int v) {
		key = v;
		visited = false;
		source = -1;
		destination = -1;
	}
	
	public Vertex(int v, int source) {
		key = v;
		visited = false;
		this.source = source;
	}
	
	public Vertex(int v, int source, int destination) {
		key = v;
		this.source = source;
		visited = false;
		this.destination = destination;
	}

}
