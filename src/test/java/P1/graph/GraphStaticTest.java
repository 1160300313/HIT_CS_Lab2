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
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    // Test Integer label
    
	@Test
	public void testAddEmpty() {
		// System.out.println("testAddEmpty");
		Graph<Integer> graph = Graph.empty();

		assertTrue(graph.vertices().isEmpty());

		// System.out.println(graph);
	}

	@Test
	public void testIntAddExist() {
		// System.out.println("testAddExist");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		graph.add(ver1);
		graph.add(ver1);
		Set<Integer> vers = graph.vertices();

		assertEquals(vers.size(), 1);
		assertTrue(vers.contains(ver1));

		// System.out.println(graph);
	}

	@Test
	public void testIntAddNotExist() {
		// System.out.println("testAddNotExist");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);

		Set<Integer> vers = graph.vertices();
		assertEquals(vers.size(), 4);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertTrue(vers.contains(ver4));

		// System.out.println(graph);
	}

	@Test
	public void testIntSetAdd() {
		// System.out.println("testIntSetAdd");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		int add1 = graph.set(ver1, ver2, 3);
		int add2 = graph.set(ver2, ver3, 1000);
		int add3 = graph.set(ver3, ver4, -1);
		int add4 = graph.set(ver2, ver1, 3);
		Set<Integer> vers = graph.vertices();

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
	public void testIntSetModify() {
		// System.out.println("testIntSetModify");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		int add = graph.set(ver1, ver2, 3);
		int mod1 = graph.set(ver1, ver2, 50);
		int mod2 = graph.set(ver1, ver2, 20);
		Set<Integer> vers = graph.vertices();

		assertEquals(add, 0);
		assertEquals(mod1, 3);
		assertEquals(mod2, 50);
		assertEquals(vers.size(), 2);

		// System.out.println(graph);
	}

	@Test
	public void testIntSetDelete() {
		// System.out.println("\ntestIntSetDelete");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.set(ver1, ver2, 3);
		graph.set(ver2, ver3, 1000);
		graph.set(ver3, ver4, 50);
		graph.set(ver2, ver1, 60);
		int dlt1 = graph.set(ver1, ver2, 0);
		int dlt2 = graph.set(ver3, ver4, 0);
		int dlt11 = graph.set(ver1, ver2, 0);
		int dlt3 = graph.set(ver2, ver4, 0);
		Set<Integer> vers = graph.vertices();

		assertEquals(dlt1, 3);
		assertEquals(dlt2, 50);
		assertEquals(dlt11, 0);
		assertEquals(dlt3, 0);
		assertEquals(vers.size(), 4);

		// System.out.println(graph);
	}

	@Test
	public void testIntRemoveNotExist() {
		// System.out.println("testIntRemoveNotExist");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		graph.remove(ver1);
		Set<Integer> vers = graph.vertices();

		assertTrue(vers.isEmpty());

		// System.out.println(graph);
	}

	@Test
	public void testIntRemoveOnlyVertex() {
		// System.out.println("testIntRemoveOnlyVertex");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);
		boolean re1 = graph.remove(ver1);
		boolean re2 = graph.remove(ver1);
		boolean re3 = graph.remove(ver3);
		Set<Integer> vers = graph.vertices();

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
	public void testIntRemoveWithEdge() {
		// System.out.println("\ntestIntRemoveWithEdge");
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.set(ver1, ver2, 3);
		graph.set(ver2, ver3, 1000);
		graph.set(ver3, ver4, 80);
		graph.set(ver4, ver1, 50);
		graph.set(ver2, ver1, 3);
		graph.remove(ver1);
		Set<Integer> vers = graph.vertices();

		assertFalse(vers.contains(ver1));
		assertEquals(vers.size(), 3);

		// System.out.println(graph);
	}

	@Test
	public void testIntVerticesEmpty() {
		Graph<Integer> graph = Graph.empty();
		Set<Integer> vers = graph.vertices();

		assertTrue(vers.isEmpty());
	}

	@Test
	public void testIntVerticesOne() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		graph.add(ver1);
		Set<Integer> vers = graph.vertices();

		assertFalse(vers.isEmpty());
		assertTrue(vers.contains(ver1));
		assertEquals(vers.size(), 1);
	}

	@Test
	public void testIntVerticesWithExist() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.add(ver1);
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);
		Set<Integer> vers = graph.vertices();

		assertEquals(vers.size(), 4);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertTrue(vers.contains(ver4));
	}

	@Test
	public void testIntVerticesUnchangable() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		Set<Integer> vers1 = graph.vertices();

		assertEquals(vers1.size(), 3);
		assertTrue(vers1.contains(ver1));
		assertTrue(vers1.contains(ver2));
		assertTrue(vers1.contains(ver3));

		vers1.remove(ver3);
		Set<Integer> vers2 = graph.vertices();

		assertEquals(vers2.size(), 3);
		assertTrue(vers2.contains(ver1));
		assertTrue(vers2.contains(ver2));
		assertTrue(vers2.contains(ver3));
	}

	@Test
	public void testIntSourcesEmpty() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		graph.add(ver1);
		Map<Integer, Integer> srcs = graph.sources(ver1);

		assertTrue(srcs.isEmpty());
	}

	@Test
	public void testIntSourcesNotExist() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Map<Integer, Integer> srcs = graph.sources(ver1);

		assertTrue(srcs.isEmpty());
	}

	@Test
	public void testIntSourcesOne() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		graph.set(ver2, ver1, 50);
		Map<Integer, Integer> srcs = graph.sources(ver1);
		Map<Integer, Integer> s = new HashMap<>();
		s.put(ver2, 50);

		assertFalse(srcs.isEmpty());
		assertEquals(srcs.size(), 1);
		// assertTrue(tars.entrySet().equals(t.entrySet()));
		assertTrue(srcs.equals(s));
	}

	@Test
	public void testIntSourcesALot() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.set(ver2, ver1, 20);
		graph.set(ver3, ver1, 30);
		graph.set(ver4, ver1, 40);
		graph.set(ver1, ver2, 10);
		Map<Integer, Integer> srcs = graph.sources(ver1);

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
	public void testIntTargetsEmpty() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		graph.add(ver1);
		Map<Integer, Integer> tars = graph.targets(ver1);

		assertTrue(tars.isEmpty());
	}

	@Test
	public void testIntTargetsNotExist() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Map<Integer, Integer> tars = graph.targets(ver1);

		assertTrue(tars.isEmpty());
	}

	@Test
	public void testIntTargetsOne() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		graph.set(ver1, ver2, 50);
		Map<Integer, Integer> tars = graph.targets(ver1);
		Map<Integer, Integer> t = new HashMap<>();
		t.put(ver2, 50);

		assertFalse(tars.isEmpty());
		assertEquals(tars.size(), 1);
		assertTrue(tars.equals(t));
	}

	@Test
	public void testIntTargetsALot() {
		Graph<Integer> graph = Graph.empty();
		Integer ver1 = 11;
		Integer ver2 = 22;
		Integer ver3 = 33;
		Integer ver4 = 44;
		graph.set(ver1, ver2, 20);
		graph.set(ver1, ver3, 30);
		graph.set(ver1, ver4, 40);
		graph.set(ver2, ver1, 10);
		Map<Integer, Integer> tars = graph.targets(ver1);

		assertFalse(tars.isEmpty());
		assertFalse(tars.containsKey(ver1));
		assertTrue(tars.containsKey(ver2));
		assertEquals((int) tars.get(ver2), 20);
		assertTrue(tars.containsKey(ver3));
		assertEquals((int) tars.get(ver3), 30);
		assertTrue(tars.containsKey(ver4));
		assertEquals((int) tars.get(ver4), 40);
	}
	
//    @Test
//    public void testCharCharLabel() {
//		Graph<Character> graph = Graph.empty();
//		char ver1 = 11;
//		char ver2 = 22;
//		char ver3 = 33;
//		char ver4 = 44;
//		graph.add(ver1);
//		graph.add(ver1);
//		graph.add(ver2);
//		graph.add(ver3);
//		graph.add(ver4);
//		Set<Character> vers1 = graph.vertices();
//		assertThat(vers1, allOf(hasItem(ver1), hasItem(ver2), hasItem(ver3), hasItem(ver4)));
//
//		int add1 = graph.set(ver1, ver2, 3);
//		int add2 = graph.set(ver2, ver3, 1000);
//		int add3 = graph.set(ver3, ver4, -1);
//		int add4 = graph.set(ver2, ver1, 3);
//
//		assertEquals(add1, 0);
//		assertEquals(add2, 0);
//		assertEquals(add3, -1);
//		assertEquals(add4, 0);
//		
//		int mod1 = graph.set(ver1, ver2, 50);
//		int mod2 = graph.set(ver1, ver2, 20);
//
//		assertEquals(mod1, 3);
//		assertEquals(mod2, 50);
//		int dlt1 = graph.set(ver1, ver2, 0);
//		int dlt2 = graph.set(ver3, ver4, 0);
//		int dlt11 = graph.set(ver1, ver2, 0);
//		int dlt3 = graph.set(ver2, ver4, 0);
//
//		assertEquals(dlt1, 20);
//		assertEquals(dlt2, 0);
//		assertEquals(dlt11, 0);
//		assertEquals(dlt3, 0);
//	}
	
	// testChar char label
	@Test
	public void testCharAddEmpty() {
		// System.out.println("testCharAddEmpty");
		Graph<Character> graph = Graph.empty();

		assertTrue(graph.vertices().isEmpty());

		// System.out.println(graph);
	}

	@Test
	public void testCharAddExist() {
		// System.out.println("testCharAddExist");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		graph.add(ver1);
		graph.add(ver1);
		Set<Character> vers = graph.vertices();

		assertEquals(vers.size(), 1);
		assertTrue(vers.contains(ver1));

		// System.out.println(graph);
	}

	@Test
	public void testCharAddNotExist() {
		// System.out.println("testCharAddNotExist");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);

		Set<Character> vers = graph.vertices();
		assertEquals(vers.size(), 4);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertTrue(vers.contains(ver4));

		// System.out.println(graph);
	}

	@Test
	public void testCharSetAdd() {
		// System.out.println("testCharSetAdd");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		int add1 = graph.set(ver1, ver2, 3);
		int add2 = graph.set(ver2, ver3, 1000);
		int add3 = graph.set(ver3, ver4, -1);
		int add4 = graph.set(ver2, ver1, 3);
		Set<Character> vers = graph.vertices();

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
	public void testCharSetModify() {
		// System.out.println("testCharSetModify");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		int add = graph.set(ver1, ver2, 3);
		int mod1 = graph.set(ver1, ver2, 50);
		int mod2 = graph.set(ver1, ver2, 20);
		Set<Character> vers = graph.vertices();

		assertEquals(add, 0);
		assertEquals(mod1, 3);
		assertEquals(mod2, 50);
		assertEquals(vers.size(), 2);

		// System.out.println(graph);
	}

	@Test
	public void testCharSetDelete() {
		// System.out.println("\ntestCharSetDelete");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.set(ver1, ver2, 3);
		graph.set(ver2, ver3, 1000);
		graph.set(ver3, ver4, 50);
		graph.set(ver2, ver1, 60);
		int dlt1 = graph.set(ver1, ver2, 0);
		int dlt2 = graph.set(ver3, ver4, 0);
		int dlt11 = graph.set(ver1, ver2, 0);
		int dlt3 = graph.set(ver2, ver4, 0);
		Set<Character> vers = graph.vertices();

		assertEquals(dlt1, 3);
		assertEquals(dlt2, 50);
		assertEquals(dlt11, 0);
		assertEquals(dlt3, 0);
		assertEquals(vers.size(), 4);

		// System.out.println(graph);
	}

	@Test
	public void testCharRemoveNotExist() {
		// System.out.println("testCharRemoveNotExist");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		graph.remove(ver1);
		Set<Character> vers = graph.vertices();

		assertTrue(vers.isEmpty());

		// System.out.println(graph);
	}

	@Test
	public void testCharRemoveOnlyVertex() {
		// System.out.println("testCharRemoveOnlyVertex");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);
		boolean re1 = graph.remove(ver1);
		boolean re2 = graph.remove(ver1);
		boolean re3 = graph.remove(ver3);
		Set<Character> vers = graph.vertices();

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
	public void testCharRemoveWithEdge() {
		// System.out.println("\ntestCharRemoveWithEdge");
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.set(ver1, ver2, 3);
		graph.set(ver2, ver3, 1000);
		graph.set(ver3, ver4, 80);
		graph.set(ver4, ver1, 50);
		graph.set(ver2, ver1, 3);
		graph.remove(ver1);
		Set<Character> vers = graph.vertices();

		assertFalse(vers.contains(ver1));
		assertEquals(vers.size(), 3);

		// System.out.println(graph);
	}

	@Test
	public void testCharVerticesEmpty() {
		Graph<Character> graph = Graph.empty();
		Set<Character> vers = graph.vertices();

		assertTrue(vers.isEmpty());
	}

	@Test
	public void testCharVerticesOne() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		graph.add(ver1);
		Set<Character> vers = graph.vertices();

		assertFalse(vers.isEmpty());
		assertTrue(vers.contains(ver1));
		assertEquals(vers.size(), 1);
	}

	@Test
	public void testCharVerticesWithExist() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.add(ver1);
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		graph.add(ver4);
		Set<Character> vers = graph.vertices();

		assertEquals(vers.size(), 4);
		assertTrue(vers.contains(ver1));
		assertTrue(vers.contains(ver2));
		assertTrue(vers.contains(ver3));
		assertTrue(vers.contains(ver4));
	}

	@Test
	public void testCharVerticesUnchangable() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		graph.add(ver1);
		graph.add(ver2);
		graph.add(ver3);
		Set<Character> vers1 = graph.vertices();

		assertEquals(vers1.size(), 3);
		assertTrue(vers1.contains(ver1));
		assertTrue(vers1.contains(ver2));
		assertTrue(vers1.contains(ver3));

		vers1.remove(ver3);
		Set<Character> vers2 = graph.vertices();

		assertEquals(vers2.size(), 3);
		assertTrue(vers2.contains(ver1));
		assertTrue(vers2.contains(ver2));
		assertTrue(vers2.contains(ver3));
	}

	@Test
	public void testCharSourcesEmpty() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		graph.add(ver1);
		Map<Character, Integer> srcs = graph.sources(ver1);

		assertTrue(srcs.isEmpty());
	}

	@Test
	public void testCharSourcesNotExist() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Map<Character, Integer> srcs = graph.sources(ver1);

		assertTrue(srcs.isEmpty());
	}

	@Test
	public void testCharSourcesOne() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		graph.set(ver2, ver1, 50);
		Map<Character, Integer> srcs = graph.sources(ver1);
		Map<Character, Integer> s = new HashMap<>();
		s.put(ver2, 50);

		assertFalse(srcs.isEmpty());
		assertEquals(srcs.size(), 1);
		// assertTrue(tars.entrySet().equals(t.entrySet()));
		assertTrue(srcs.equals(s));
	}

	@Test
	public void testCharSourcesALot() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.set(ver2, ver1, 20);
		graph.set(ver3, ver1, 30);
		graph.set(ver4, ver1, 40);
		graph.set(ver1, ver2, 10);
		Map<Character, Integer> srcs = graph.sources(ver1);

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
	public void testCharTargetsEmpty() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		graph.add(ver1);
		Map<Character, Integer> tars = graph.targets(ver1);

		assertTrue(tars.isEmpty());
	}

	@Test
	public void testCharTargetsNotExist() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Map<Character, Integer> tars = graph.targets(ver1);

		assertTrue(tars.isEmpty());
	}

	@Test
	public void testCharTargetsOne() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		graph.set(ver1, ver2, 50);
		Map<Character, Integer> tars = graph.targets(ver1);
		Map<Character, Integer> t = new HashMap<>();
		t.put(ver2, 50);

		assertFalse(tars.isEmpty());
		assertEquals(tars.size(), 1);
		assertTrue(tars.equals(t));
	}

	@Test
	public void testCharTargetsALot() {
		Graph<Character> graph = Graph.empty();
		Character ver1 = 11;
		Character ver2 = 22;
		Character ver3 = 33;
		Character ver4 = 44;
		graph.set(ver1, ver2, 20);
		graph.set(ver1, ver3, 30);
		graph.set(ver1, ver4, 40);
		graph.set(ver2, ver1, 10);
		Map<Character, Integer> tars = graph.targets(ver1);

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
