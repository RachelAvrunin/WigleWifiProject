
public interface Filter {
	boolean test (RowsRead item);
}
/**
 * @author Rachel
 * 
 * this interface is for filtering the ArrayList for the Kml files
 * it help's us by no needing a full class for each filter type  
 */



class IdFilter implements Filter {
	private String id;
	/**
	 * @param id
	 * this function implements the filter class 
	 * and filter by id
	 */
	public IdFilter(String id) {
		this.id = id;
	}

	@Override
	public boolean test(RowsRead item) {
		return item.id.equals(id);
	}
}

class TimeFilter implements Filter {
	private String time;

	/**
	 * @param time
	 * this function implements the filter class 
	 * and filter by time
	 */
	public TimeFilter(String time) {
		this.time = time;
	}

	@Override
	public boolean test(RowsRead item) {
		return item.time.equals(time);
	}
}
class LocationFilter implements Filter {
	private double lat, lon,alt;

	/**
	 * @param lon
	 * @param lat
	 * @param alt
	 *  
	 * this function implements the filter class 
	 * and filter by radius of location 
	 */
	public LocationFilter(double lon,double lat,double alt) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}

	@Override
	public boolean test(RowsRead item) {
		double dist=Math.sqrt(Math.pow(item.latitude-lat, 2)+Math.pow(item.longtitude-lon, 2)+Math.pow(item.altitude-alt, 2));
		return (dist<0.005);
	}
}