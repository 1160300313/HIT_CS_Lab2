/**
 * 
 */
package P3;

/**
 * @author EvaneScencE
 *
 */
public interface Stop {
	/**
	 * return the name of the stop.
	 * 
	 * @return name
	 */
	public String getName();

	/**
	 * 返回纬度
	 * 
	 * @return 车站的维度
	 */
	public double getLatitude();

	/**
	 * 返回经度
	 * 
	 * @return 车站的经度
	 */
	public double getLongitude();

	public int getStopTime();
}

class BusStop implements Stop {

	private String name;
	private double latitude;
	private double longitude;
	private int stopTime;

	public BusStop(String name, double latitude, double longitude, int stopTime) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.stopTime = stopTime;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public int getStopTime() {
		return stopTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		// result = prime * result + stopTime;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusStop other = (BusStop) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		// if (stopTime != other.stopTime)
		// return false;
		return true;
	}

	@Override
	public String toString() {
		return name + "," + latitude + "," + longitude + "," + stopTime;
	}

}