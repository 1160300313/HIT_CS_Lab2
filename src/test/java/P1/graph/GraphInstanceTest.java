/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

	// Testing strategy
	// TODO

	/**
	 * Overridden by implementation-specific test classes.
	 * 
	 * @return a new empty graph of the particular implementation being tested
	 */
	public abstract Graph<String> emptyInstance();

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void testInitialVerticesEmpty() {
		// TODO you may use, change, or remove this test
		assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
	}

	// TODO other tests for instance methods of Graph
	/*
	 * Graph.add() 
	 * 		no input
	 * 		exist 
	 * 		not exist 
	 * Graph.set() 
	 * 		add 
	 * 		modify 
	 * 		delete
	 * Graph.remove() 
	 * 		not exist 
	 * 		only vertex(s) 
	 * 		with edge(s) 
	 * Graph.vertices()
	 * 		empty 
	 * 		one 
	 * 		a lot with chongfu element 
	 * 		unable to be change 
	 * Graph.sources()
	 * 		empty 
	 * 		not exist 
	 * 		one edge
	 * 		a lot edges 
	 * Graph.targets() 
	 * 		same above
	 */

	@Test
	public void testAddEmpty() {
		// System.out.println("testAddEmpty");
		Graph<String> graph = emptyInstance();

		assertTrue(graph.vertices().isEmpty());

		// System.out.println(graph);
	}

	@Test
	public void testAddExist() {
		// System.out.println("testAddExist");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		graph.add(ver1);
		graph.add(ver1);
		Set<String> vers = graph.vertices();

		assertEquals(vers.size(), 1);
		assertTrue(vers.contains(ver1));

		// System.out.println(graph);
	}

	@Test
	public void testAddNotExist() {
		// System.out.println("testAddNotExist");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);

		Set<String> vers = graph.vertices();
		assertEquals(vers.size(), 4);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertTrue(vers.contains(ver4));

		// System.out.println(graph);
	}

	@Test
	public void testSetAdd() {
		// System.out.println("testSetAdd");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		int add1 = graph.set(ver1, ver2, 3);
		int add2 = graph.set(ver2, ver3, 1000);
		int add3 = graph.set(ver3, ver4, -1);
		int add4 = graph.set(ver2, ver1, 3);
		Set<String> vers = graph.vertices();

		assertEquals(add1, 0);
		assertEquals(add2, 0);
		assertEquals(add3, -1);
		assertEquals(add4, 0);
		assertEquals(vers.size(), 3);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertFalse(vers.contains(ver4));

		// System.out.println(graph);
	}

	@Test
	public void testSetModify() {
		// System.out.println("testSetModify");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		int add = graph.set(ver1, ver2, 3);
		int mod1 = graph.set(ver1, ver2, 50);
		int mod2 = graph.set(ver1, ver2, 20);
		Set<String> vers = graph.vertices();

		assertEquals(add, 0);
		assertEquals(mod1, 3);
		assertEquals(mod2, 50);
		assertEquals(vers.size(), 2);

		// System.out.println(graph);
	}

	@Test
	public void testSetDelete() {
		// System.out.println("\ntestSetDelete");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.set(ver1, ver2, 3);
		graph.set(ver2, ver3, 1000);
		graph.set(ver3, ver4, 50);
		graph.set(ver2, ver1, 60);
		int dlt1 = graph.set(ver1, ver2, 0);
		int dlt2 = graph.set(ver3, ver4, 0);
		int dlt11 = graph.set(ver1, ver2, 0);
		int dlt3 = graph.set(ver2, ver4, 0);
		Set<String> vers = graph.vertices();

		assertEquals(dlt1, 3);
		assertEquals(dlt2, 50);
		assertEquals(dlt11, 0);
		assertEquals(dlt3, 0);
		assertEquals(vers.size(), 4);

		// System.out.println(graph);
	}

	@Test
	public void testRemoveNotExist() {
		// System.out.println("testRemoveNotExist");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		graph.remove(ver1);
		Set<String> vers = graph.vertices();

		assertTrue(vers.isEmpty());

		// System.out.println(graph);
	}

	@Test
	public void testRemoveOnlyVertex() {
		// System.out.println("testRemoveOnlyVertex");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);
		boolean re1 = graph.remove(ver1);
		boolean re2 = graph.remove(ver1);
		boolean re3 = graph.remove(ver3);
		Set<String> vers = graph.vertices();

		assertTrue(re1);
		assertFalse(re2);
		assertTrue(re3);
		assertEquals(vers.size(), 2);
		assertFalse(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertFalse(vers.contains(ver3));
		assertTrue(vers.contains(ver4));

		// System.out.println(graph);
	}

	@Test
	public void testRemoveWithEdge() {
		// System.out.println("\ntestRemoveWithEdge");
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.set(ver1, ver2, 3);
		graph.set(ver2, ver3, 1000);
		graph.set(ver3, ver4, 80);
		graph.set(ver4, ver1, 50);
		graph.set(ver2, ver1, 3);
		graph.remove(ver1);
		Set<String> vers = graph.vertices();

		assertFalse(vers.contains(ver1));
		assertEquals(vers.size(), 3);

		// System.out.println(graph);
	}

	@Test
	public void testVerticesEmpty() {
		Graph<String> graph = emptyInstance();
		Set<String> vers = graph.vertices();

		assertTrue(vers.isEmpty());
	}

	@Test
	public void testVerticesOne() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		graph.add(ver1);
		Set<String> vers = graph.vertices();

		assertFalse(vers.isEmpty());
		assertTrue(vers.contains(ver1));
		assertEquals(vers.size(), 1);
	}

	@Test
	public void testVerticesWithExist() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.add(ver1);
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);
		Set<String> vers = graph.vertices();

		assertEquals(vers.size(), 4);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertTrue(vers.contains(ver4));
	}

	@Test
	public void testVerticesUnchangable() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		Set<String> vers1 = graph.vertices();

		assertEquals(vers1.size(), 3);
		assertTrue(vers1.contains(ver1));
		assertTrue(vers1.contains(ver2));
		assertTrue(vers1.contains(ver3));

		vers1.remove(ver3);
		Set<String> vers2 = graph.vertices();

		assertEquals(vers2.size(), 3);
		assertTrue(vers2.contains(ver1));
		assertTrue(vers2.contains(ver2));
		assertTrue(vers2.contains(ver3));
	}

	@Test
	public void testSourcesEmpty() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		graph.add(ver1);
		Map<String, Integer> srcs = graph.sources(ver1);

		assertTrue(srcs.isEmpty());
	}

	@Test
	public void testSourcesNotExist() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		Map<String, Integer> srcs = graph.sources(ver1);

		assertTrue(srcs.isEmpty());
	}

	@Test
	public void testSourcesOne() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		graph.set(ver2, ver1, 50);
		Map<String, Integer> srcs = graph.sources(ver1);
		Map<String, Integer> s = new HashMap<>();
		s.put(ver2, 50);

		assertFalse(srcs.isEmpty());
		assertEquals(srcs.size(), 1);
		// assertTrue(tars.entrySet().equals(t.entrySet()));
		assertTrue(srcs.equals(s));
	}

	@Test
	public void testSourcesALot() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.set(ver2, ver1, 20);
		graph.set(ver3, ver1, 30);
		graph.set(ver4, ver1, 40);
		graph.set(ver1, ver2, 10);
		Map<String, Integer> srcs = graph.sources(ver1);

		assertFalse(srcs.isEmpty());
		assertFalse(srcs.containsKey(ver1));
		assertTrue(srcs.containsKey(ver2));
		assertEquals((int) srcs.get(ver2), 20);
		assertTrue(srcs.containsKey(ver3));
		assertEquals((int) srcs.get(ver3), 30);
		assertTrue(srcs.containsKey(ver4));
		assertEquals((int) srcs.get(ver4), 40);
	}

	@Test
	public void testTargetsEmpty() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		graph.add(ver1);
		Map<String, Integer> tars = graph.targets(ver1);

		assertTrue(tars.isEmpty());
	}

	@Test
	public void testTargetsNotExist() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		Map<String, Integer> tars = graph.targets(ver1);

		assertTrue(tars.isEmpty());
	}

	@Test
	public void testTargetsOne() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		graph.set(ver1, ver2, 50);
		Map<String, Integer> tars = graph.targets(ver1);
		Map<String, Integer> t = new HashMap<>();
		t.put(ver2, 50);

		assertFalse(tars.isEmpty());
		assertEquals(tars.size(), 1);
		assertTrue(tars.equals(t));
	}

	@Test
	public void testTargetsALot() {
		Graph<String> graph = emptyInstance();
		String ver1 = "vertex1";
		String ver2 = "vertex2";
		String ver3 = "vertex3";
		String ver4 = "vertex4";
		graph.set(ver1, ver2, 20);
		graph.set(ver1, ver3, 30);
		graph.set(ver1, ver4, 40);
		graph.set(ver2, ver1, 10);
		Map<String, Integer> tars = graph.targets(ver1);

		assertFalse(tars.isEmpty());
		assertFalse(tars.containsKey(ver1));
		assertTrue(tars.containsKey(ver2));
		assertEquals((int) tars.get(ver2), 20);
		assertTrue(tars.containsKey(ver3));
		assertEquals((int) tars.get(ver3), 30);
		assertTrue(tars.containsKey(ver4));
		assertEquals((int) tars.get(ver4), 40);
	}
}
