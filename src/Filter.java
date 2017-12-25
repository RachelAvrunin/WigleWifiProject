	/**
	 * this interface is for filtering the ArrayList for the Kml files
	 * it help's us by no needing a full class for each filter type  
	 * 
	 * @author Rachel
	 */
public interface Filter {
	boolean test (RowsRead item);

}

class IdFilter implements Filter {
	private String id;
	
	/**
	 * this function implements the filter class 
	 * and filter by id
	 * 
	 * @param id
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
	private String date;

	/**
	 * this function implements the filter class 
	 * and filter by time
	 * 
	 * @param time
	 */
	public TimeFilter(String date,String time) {
		this.time = time;
		this.date = date;
	}

	@Override
	public boolean test(RowsRead item) {
		return((item.time.equals(time)) && (item.date.equals(date)));
	}
}
class LocationFilter implements Filter {
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
	public boolean test(RowsRead item) {
		double dist=Math.sqrt(Math.pow(item.latitude-lat, 2)+Math.pow(item.longtitude-lon, 2));
		return (dist<0.005);
	}
}