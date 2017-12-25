/**
 * this class represent the point in the map -lat , lon and alt
 * 
 * @author Rachel
 */
public class Point3D {

	double longtitude,latitude, altitude;

	/**
	 * contractor for the class
	 * 
	 * @param longtitude
	 * @param latitude
	 * @param altitude
	 */
	public Point3D(double longtitude, double latitude, double altitude) {

		this.longtitude = longtitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}

	/**
	 * toString function for the point
	 */
	public String toString() {
		return latitude + "," + longtitude + "," + altitude;
	}



}
