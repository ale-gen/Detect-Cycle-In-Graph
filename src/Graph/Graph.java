package Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import Stack.Stack;

public class Graph {
	
	private Vertex vertexTab[];
	private int adjMatrix[][];
	private int vertexNumber;
	private Stack stack;
	private ArrayList<Integer> vertex = new ArrayList<Integer>();
	
	private static final String INF = "&";
	private static final int INFINITY = 999999999;
	
	public Graph(int size) {
		vertexTab = new Vertex[size];
		adjMatrix = new int[size][size];
		vertexNumber = 0;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				adjMatrix[i][j] = 0;
			}
		}
		
		stack = new Stack(size);
	}
	
	public Graph() throws IOException {
		getGraphFromFile();
	}
	
	public int getVertexNumber() {
		return vertexNumber;
	}
	
	public ArrayList<Integer> getVertex() {
		return vertex;
	}

	public void setVertex(ArrayList<Integer> vertex) {
		this.vertex = vertex;
	}

	//Reading the adjacency matrix from the file
	public void getGraphFromFile() {
	       
		int row, col;
		String line, current;
		StringTokenizer st;
        final File file = new File("graph.txt");
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            st = new StringTokenizer(line);
            current = st.nextToken();
            vertexNumber = Integer.parseInt(current);
            vertexTab = new Vertex[vertexNumber];
            stack = new Stack(vertexNumber);
            
            for (int i = 0; i < vertexNumber; i++) {
            	vertexTab[i] = new Vertex(i);
            }
            
            adjMatrix = new int[vertexNumber][vertexNumber];
            row = 0;
            
            while((line = bufferedReader.readLine()) != null) {
            	st = new StringTokenizer(line);
            	col = 0;
            	
            	while( col < vertexNumber && st.hasMoreTokens()) {
            		current = st.nextToken();
            		if (current.compareTo(INF) == 0) 
            			adjMatrix[row][col++] = INFINITY;
            		else 
            			adjMatrix[row][col++] = Integer.parseInt(current);
            	}
            	row++;
            }
            
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void addVertex(int v) {
		vertexTab[vertexNumber++] = new Vertex(v);
	}
	
	public void addVertex(int v, int index) {
		vertexTab[index] = new Vertex(v);
	}
	
	public void addEdge(int start, int end) {
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}
	
	public void displayVertex(int v) {
		System.out.println(vertexTab[v].key);
	}
	
	//Method to display adjacency matrix
	public void display() {
		for (int i = 0; i < vertexNumber; i++) {
			for (int j = 0; j < vertexNumber; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	int cycleFind = 0;
	
	//Methods to find cycle with showing the pairs of vertices
	public Stack dfsCycle() {
		vertexTab[0].visited = true;
		//displayVertex(0);
		vertexTab[0].source = -1;
		vertexTab[0].destination = 0;
		stack.push(vertexTab[0]);
		Vertex leaf;
		
		while (!stack.isEmpty() || cycleFind == 1) {
			Vertex previous = stack.peek();
			//System.out.println(previous.key + " / " + previous.source + " / " + previous.destination);
			Vertex v = getUnvisitedVertex(stack.peek(), stack.peek().source, stack.peek().destination);
			if (v == null) {
				leaf = stack.pop();
				//System.out.println("Zdejmujemy ze stosu: " + leaf.key);
				leaf.source = stack.peek().key;
				stack.peek().destination = leaf.key;
			} else if (v.key == previous.key) {
				if (stack.size() > 2) {
					cycleFind = 1;
					return stack;
				} else {
					stack.pop();
				}
			} else {
				vertexTab[v.key].visited = true;
				//displayVertex(v.key);
				v.source = stack.peek().key;
				stack.push(v);
			}
		}
		for (int i = 0; i < vertexNumber; i++) {
			vertexTab[i].visited = false;
		}
		return stack;
	}
	
	public Vertex getUnvisitedVertex(Vertex v, int source, int destination) {
		for (int i = 0; i < vertexNumber; i++) {
			if (adjMatrix[v.key][i] == 1 && vertexTab[i].visited == false) {
				return vertexTab[i];
			} else if (adjMatrix[v.key][i] == 1  && vertexTab[i].visited == true &&  vertexTab[i].key != source && vertexTab[i].key != destination) {
				return v;
			}
		}
		return null;
	}
	//
	
	// Methods, which allow to detect cycle, but don't show the pairs of vertex belonging to cycle
	//
	public boolean hasCycle2(int start) {
		if (start > vertexNumber)
			throw new IllegalArgumentException("No such vertex!");
		
		boolean[] visited = new boolean[vertexNumber];
		
		return dfs(start, visited);
	}
	
	public boolean dfs(int v, boolean[] visited) {
		visited[v] = true;
		
		for (Integer w : connectedTo(v)) {
			if (visited[w] || dfs(w, visited)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Integer> connectedTo(int v) {
		List<Integer> connected = new ArrayList<>();
		
		for (int i = v; i < vertexNumber; i++) {
			if (adjMatrix[v][i] == 1) {
				connected.add(i);
			}
		}
		return connected;
	}
	//

}
