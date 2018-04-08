/**
 * 
 */
package P3;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author EvaneScencE
 *
 */
public class StopTest {

	@Test
	public void equalsTest() {
		Stop st1 = new BusStop("5TH AVE AT WOOD ST",40.440785,-80.000814,25620);
		Stop st2 = new BusStop("5TH AVE AT WOOD ST",40.440785,-80.000814,69720);
		
		assertTrue(st1.equals(st2));
	}

}
