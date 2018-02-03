package filters;
import project.*;

public class LocationFilter implements Filter {
	private double lat, lon;

	/**
	 * this function implements the filter class 
	 * and filter by radius of location 
	 * 
	 * @param lon
	 * @param lat
	 * @param alt
	 */
	public LocationFilter(double lat,double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public boolean test(WifiScan item) {
		double dist=Math.sqrt(Math.pow(item.p.latitude-lat, 2)+Math.pow(item.p.longtitude-lon, 2));
		return (dist<0.005);
	}
}
