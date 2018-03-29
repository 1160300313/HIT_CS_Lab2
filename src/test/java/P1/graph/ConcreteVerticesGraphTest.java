/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		// System.out.println();
		return new ConcreteVerticesGraph<String>();
	}

	/*
	 * Testing ConcreteVerticesGraph...
	 */

	// Testing strategy for ConcreteVerticesGraph.toString()
	// TODO

	// TODO tests for ConcreteVerticesGraph.toString()

	@Test
	public void testTostringEmpty() {
		Graph<String> graph = emptyInstance();
		String str = "ConcreteVerticesGraph [vertices=\n]\n";

		assertEquals(graph.toString(), str);
	}
	/*
	 * Testing Vertex...
	 */

	// Testing strategy for Vertex
	// toString

	// TODO tests for operations of Vertex

	@Test
	public void testVertexTostring() {
		String sv1 = "Vertex1";
		String sv2 = "Vertex2";
		Vertex<String> ver1 = new Vertex<String>(sv1);
		ver1.setTargets(sv2, 999);

		assertThat(ver1.toString(), allOf(containsString("name=" + ver1.getName()), containsString(sv2 + "=" + 999)));
	}
}
