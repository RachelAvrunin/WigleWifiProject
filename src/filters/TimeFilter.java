package filters;

import project.WifiScan;

public class TimeFilter implements Filter {
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
	public boolean test(WifiScan item) {
		return((item.time.equals(time)) && (item.date.equals(date)));
	}
}
