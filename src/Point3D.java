
public class Point3D {

	double longtitude,latitude, altitude;

	public Point3D(double longtitude, double latitude, double altitude) {

		this.longtitude = longtitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}
	
	public String toString() {
		return latitude + "," + longtitude + "," + altitude;
	}


	
}
