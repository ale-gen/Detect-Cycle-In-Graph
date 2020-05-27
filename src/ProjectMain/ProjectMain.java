package ProjectMain;

import java.io.IOException;
import java.util.ArrayList;

import Graph.Graph;
import Graph.Vertex;
import Stack.Stack;

public class ProjectMain {
	
	public static void main(String[] args) throws IOException {
		
		Graph graph = new Graph();
		Stack stack = new Stack();
		ArrayList<Vertex> vertexInCycle = new ArrayList<Vertex>();
		graph.display();
		stack = graph.dfsCycle();
		if (stack.size() > 0) {
			System.out.println("The cycle has been found!");
			while (!stack.isEmpty()) {
				Vertex v = stack.pop();
				vertexInCycle.add(v);
			}
			System.out.println("The pairs of vertexes of cycle: ");
			for (int i = vertexInCycle.size()-1; i >= 0; i--) {
				if (i > 0) 
					System.out.println(vertexInCycle.get(i).key + "-" + vertexInCycle.get(i-1).key);
				else 
					System.out.println(vertexInCycle.get(i).key + "-" + vertexInCycle.get(vertexInCycle.size()-1).key);
			}
		} else {
			System.out.println("The cycle hasn't been found");
		}
		
		
		/*if (graph.hasCycle()) {
			System.out.println("The graph has cycle"); 
		} else {
			System.out.println("The graph has no cycle");
		}*/
		
		/*if (!graph.hasCycle2(0))
			System.out.println("No cycle");
		else 
			System.out.println("There is a cycle!");
		System.out.println(graph.getVertex());*/
		
	}

}
