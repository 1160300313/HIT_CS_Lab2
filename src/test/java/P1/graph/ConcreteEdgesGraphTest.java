/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
    	//System.out.println("New concreteEdgesGraph");
    	
        return new ConcreteEdgesGraph<String>();
    }
    
	/*
	 * Testing ConcreteEdgesGraph...
	 */

    @Test
	public void testEmptyGraph() {
		Graph<String> g = new ConcreteEdgesGraph<String>();
		assertTrue(g.vertices().isEmpty());
	}
    
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    
	// TODO tests for ConcreteEdgesGraph.toString()
	@Test
	public void testTostringEmpty() {
		Graph<String> graph = emptyInstance();
		// System.out.println(graph);

		assertEquals(graph.toString(), "vertices=\n[]\nedges=\n[]\n");
	}

	@Test
	public void testTostringOnlyVextex() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		graph.add(ver1);
		graph.add(ver2);
		// System.out.println(graph);
		String string = "vertices=\n" + Arrays.asList(ver1, ver2) + "\nedges=\n[]\n";
		assertEquals(graph.toString(), string);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testTostringWithEdge() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.set(ver1, ver2, 20);
		graph.set(ver1, ver3, 30);
		graph.set(ver1, ver4, 40);
		graph.set(ver2, ver1, 10);

		Edge<String> ed1 = new Edge<String>(ver1, ver2, 20);
		Edge<String> ed2 = new Edge<String>(ver1, ver3, 30);
		Edge<String> ed3 = new Edge<String>(ver1, ver4, 40);
		Edge<String> ed4 = new Edge<String>(ver2, ver1, 10);
		String string = "vertices=\n" + Arrays.asList(ver4, ver1, ver3, ver2) + "\nedges=\n"
				+ Arrays.asList(ed1, ed2, ed3, ed4) + "\n";
		// System.out.println(graph);
		// System.out.println(string);

		assertEquals(graph.toString(), string);
		// assertThat ≤‚ ‘
		assertThat(graph.toString(),
				allOf(containsString(ver1), containsString(ver2), containsString(ver3), containsString(ver4),
						containsString(ed1.toString()), containsString(ed2.toString()), containsString(ed3.toString()),
						containsString(ed4.toString())));
	}
	/*
	 * Testing Edge...
	 */
    
    // Testing strategy for Edge
	// gets immutable contains equal toString 

	// TODO tests for operations of Edge

	@Test
	public void testEdgeGets() {
		String source = "source";
		String target = "target";
		int weight = 6;
		Edge<String> ed1 = new Edge<String>(source, target, weight);
		Edge<String> ed2 = new Edge<String>(null, null, 0);
		assertEquals(ed1.getSource(), source);
		assertEquals(ed1.getTarget(), target);
		assertEquals(ed1.getWeight(), weight);
		assertEquals(ed2.getSource(), null);
		assertEquals(ed2.getTarget(), null);
		assertEquals(ed2.getWeight(), 0);
	}

	@Test
	public void testEdgeContains() {
		String source = "source";
		String target = "target";
		int weight = 6;
		Edge<String> ed1 = new Edge<String>(source, target, weight);
		
		assertTrue(ed1.contain(source));
		assertTrue(ed1.contain(target));
		assertTrue(ed1.containSource(source));
		assertTrue(ed1.containTarget(target));
	}
	
	@Test
	public void testEdgeEquals() {
		String source = "source";
		String target = "target";
		int weight1 = 6;
		int weight2 = 8;
		Edge<String> ed1 = new Edge<String>(source, target, weight1);
		Edge<String> ed2 = new Edge<String>(source, target, weight2);
		Edge<String> ed3 = new Edge<String>(target, source, weight2);
		
		assertTrue(ed1.equals(ed2));
		assertTrue(ed2.equals(ed1));
		assertFalse(ed1.equals(ed3));
		assertFalse(ed2.equals(ed3));
		assertFalse(ed3.equals(ed1));
	}
	
	@Test
	public void testEdgeToString() {
		String source = "source";
		String target = "target";
		int weight = 6;
		Edge<String> ed1 = new Edge<String>(source, target, weight);
		String string = "Source:" + source + "\tTarget:" + target + "\tWeight:" + weight + "\n";

		assertEquals(ed1.toString(), string);
	}
}
