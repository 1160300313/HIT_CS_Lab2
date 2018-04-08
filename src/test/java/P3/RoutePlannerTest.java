package P3;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class RoutePlannerTest {

	@Test
	public void findSubstringTest() {
		RoutePlannerBuilder bRoutePlannerBuilder = new BusRoutePlannerBuilder();
		BusRoutePlanner rPlanner = (BusRoutePlanner) bRoutePlannerBuilder.build("src/main/java/P3/in-hw2.txt", 1200);
		List<Stop> list = rPlanner.findStopBySubstring("5th");
		// Itinerary aaa = rPlanner.computeRoute(list.get(0), list.get(1), 23000);
		assertEquals(list.size(), 3);
	}

}
