package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoutePlannerBuilderTest {

	@Test
	public void initTest() {
		RoutePlannerBuilder rbp = new BusRoutePlannerBuilder();
		@SuppressWarnings("unused")
		BusRoutePlanner rPlanner = (BusRoutePlanner) rbp.build("src/main/java/P3/in-hw2.txt", 1200);
		// Graph<Stop> graph = rPlanner.getGraph();
		// System.out.println(graph.vertices().size());
		assertTrue(true);
	}

}
