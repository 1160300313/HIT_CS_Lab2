/**
 * 
 */
package P3;

import java.util.Arrays;

/**
 * @author EvaneScencE
 *
 */
public class Itinerary {
	private String name;
	private int startTime;
	private int endTime;
	// private List<Stop> route = new LinkedList<>();
	private Stop[] stops = new Stop[9999];

	public Itinerary(String name, int startTime, int endTime, Stop[] stops) {
		super();
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.stops = stops;
	}

	/**
	 * 
	 * @return
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @return
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @return
	 */
	public int getWaitTime() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public Stop getStartLocation() {
		return stops[0];
	}

	/**
	 * 
	 * @return
	 */
	public Stop getEndLocation() {
		return stops[stops.length - 1];
	}

	/**
	 * 
	 * @return
	 */
	public String getInstructions() {
		return "Itinerary [name=" + name + ", startTime=" + startTime + ", endTime=" + endTime + ", stops="
				+ Arrays.toString(stops) + "]";
	}

}
