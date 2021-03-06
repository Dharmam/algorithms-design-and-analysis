package challenges.assorted.graph;

import static challenges.assorted.graph.FindCycleInAGraph._hasCycle;
import static challenges.assorted.graph.GraphUtilities.printVertexStack;
import static challenges.assorted.graph.GraphUtilities.resetGraph;
import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;
import challenges.assorted.graph.model.Edge;
import challenges.assorted.graph.model.Graph;
import challenges.assorted.graph.model.Vertex;

/**
 * Program to compute the topological ordering of a directed acyclic graph.
 * 
 * @author Hxkandwal
 *
 */
public class TopologicalOrder extends AbstractCustomTestRunner {
	
	private static TopologicalOrder _instance = new TopologicalOrder();
	
	public String _topologicalOrdering(Graph graph) {
		Stack<Vertex> orderedStack = new Stack<>();
		
		for (Vertex vertex : graph.getVertices())
			if (!vertex.isSeen() && vertex.getRevAdjacentEdges().size() == 0)
				performDFS(vertex, orderedStack);
		
		return printVertexStack(orderedStack);
	}
	
	private void performDFS(Vertex vertex, Stack<Vertex> collector) {
		if (vertex.getAdjacentEdges().size() > 0) {
			for (Edge edge : vertex.getAdjacentEdges()) {
				Vertex otherVertex = edge.otherEnd(vertex);
				
				if (!otherVertex.isSeen())
					performDFS (otherVertex, collector);
			}
		}
		
		collector.push(vertex);
		vertex.setSeen(true);
	}
	
    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        testComplex("/src/test/resources/challenges/assorted/graph/graph-topological-order-1.txt", "7,6,5,4,2,3,1");
        testComplex("/src/test/resources/challenges/assorted/graph/graph-topological-order-2.txt", "5,4,3,1,2");
        testComplex("/src/test/resources/challenges/assorted/graph/graph-topological-order-3.txt", "2,5,4,3,1");
        testComplex("/src/test/resources/challenges/assorted/graph/graph-topological-order-4.txt", "2,5,3,1,4");
        testComplex("/src/test/resources/challenges/assorted/graph/graph-find-cycle-1.txt", "");
    }

    private static void testComplex(final String filename, final String expectedOutput) throws FileNotFoundException {
        Graph graph = Graph.readGraph(new Scanner(new File(System.getProperty("user.dir") + filename)), true);

        if (!_hasCycle(graph)) {
        	resetGraph(graph);
        	
			_instance.runTest(graph, expectedOutput);
        } else
        	System.out.println("graph has a cycle!");
    }

    public void runTest(final Graph graph, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { graph });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
}
