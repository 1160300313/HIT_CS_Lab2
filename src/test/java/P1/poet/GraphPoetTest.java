/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

	// Testing strategy
	// TODO

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	// TODO tests

	@Test
	public void test1() throws IOException {
		String name = "P1/poet/seven-words.txt";
		File file = new File(name);
		GraphPoet graphPoet = new GraphPoet(file);
		String input = "Seek to explore new and exciting synergies!";
		String poem = graphPoet.poem(input);
		String actual = "Seek to explore strange new life and exciting synergies!";
		assertEquals(poem, actual);
	}

	@Test
	public void test2() throws IOException {
		String name = "P1/poet/mugar-omni-theater.txt";
		File file = new File(name);
		GraphPoet graphPoet = new GraphPoet(file);
		String input = "Test the system.";
		String poem = graphPoet.poem(input);
		String actual = "Test of the system.";
		assertEquals(poem, actual);
	}

	@Test
	public void testMulWeight() throws IOException {
		String name = "P1/poet/multi-weight.txt";
		File file = new File(name);
		GraphPoet graphPoet = new GraphPoet(file);
		String input = "hello, goodbye!";
		String poem = graphPoet.poem(input);
		String actual = "hello, hello, goodbye!";
		assertEquals(poem, actual);
	}

	@Test
	public void testTostring() throws IOException {
		String name = "P1/poet/seven-words.txt";
		File file = new File(name);
		GraphPoet graphPoet = new GraphPoet(file);
		// System.out.println(graphPoet);
		String actual = "GraphPoet [graph=vertices=\n[new, worlds, explore, and, to, civilizations, seek, strange, life, out]\nedges=\n[Source:to	Target:explore	Weight:1\n, Source:explore	Target:strange	Weight:1\n, Source:strange	Target:new	Weight:1\n, Source:new	Target:worlds	Weight:1\n, Source:worlds	Target:to	Weight:1\n, Source:to	Target:seek	Weight:1\n, Source:seek	Target:out	Weight:1\n, Source:out	Target:new	Weight:1\n, Source:new	Target:life	Weight:1\n, Source:life	Target:and	Weight:1\n, Source:and	Target:new	Weight:1\n, Source:new	Target:civilizations	Weight:1\n]\n]";

		assertEquals(graphPoet.toString(), actual);
	}
}
