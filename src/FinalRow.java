import java.util.ArrayList;

public class FinalRow{
	/**
	 * @author Rachel
	 * 
	 *This class represents the final wifi rows we read 
	 *after filtering the 10 strongest
	 *
	 */

	String id,date,time;
	double longtitude,latitude, altitude;
	ArrayList<wifi> wifis = new ArrayList<wifi>();

	/**
	 * @param date
	 * @param time
	 * @param id
	 * @param latitude
	 * @param longtitude
	 * @param altitude
	 * 
	 * the constructor
	 */
	public FinalRow( String date,String time, String id, double latitude, double longtitude, double altitude ) {

		this.date = date;
		this.time = time;
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.altitude = altitude;

	}

	/**
	 * @param Signal
	 * @param mac
	 * @param SSID
	 * @param frequncy
	 * 
	 * this sends the elements to the wifi constructor
	 * 
	 */
	public void addline(int Signal, String mac, String SSID, int frequncy) {

		this.wifis.add(new wifi(Signal,mac,SSID,frequncy));
	}

	/**
	 * toString function
	 * removes the '[' and the ']'chars from the wifi ArrayList
	 */
	public String toString() {
		String ans= date + "," + time + "," + id + "," + latitude + "," + longtitude + "," + altitude + "," + wifis.toString();
		for (int i = 0; i < ans.length(); i++){
			if (ans.charAt(i)=='[')
				ans=ans.substring(0, i) + ans.substring(i+1,ans.length());
			if (ans.charAt(i)==']')
				ans=ans.substring(0, i);
		}
		return ans + System.lineSeparator();
	}
}
