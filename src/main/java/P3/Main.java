/**
 * 
 */
package P3;

/**
 * @author EvaneScencE
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RoutePlannerBuilder rpb = new BusRoutePlannerBuilder();
		@SuppressWarnings("unused")
		RoutePlanner rPlanner = rpb.build("src/main/java/P3/in-hw2.txt", 12000);
		
	}

}
