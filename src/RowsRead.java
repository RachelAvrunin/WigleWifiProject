
public class RowsRead implements Comparable <RowsRead> {
	/**
	 * This class represents the wifi rows we read 
	 * without filtering the 10 strongest
	 * just one wifi in a row
	 * 
	 * @author Rachel
	 */

	String mac,SSID,date,time,id;
	int Signal, frequncy ;
	double longtitude,latitude, altitude;

	/**
	 * the constructor 
	 * 
	 * @param date
	 * @param time
	 * @param id
	 * @param latitude
	 * @param longtitude
	 * @param altitude
	 * @param Signal
	 * @param SSID
	 * @param mac
	 * @param frequncy
	 */
	public RowsRead( String date ,String time, String id, double latitude, double longtitude, double altitude, int Signal, String SSID,String mac , int frequncy) {

		this.date = date;
		this.time = time;
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.altitude = altitude;
		this.Signal = Signal;
		this.SSID = SSID;
		this.mac = mac;
		this.frequncy = frequncy;
	}
	/**
	 * Copy constructor
	 * 
	 * @param r
	 */
	public RowsRead( RowsRead r) {

		this.date = r.date;
		this.time = r.time;
		this.id = r.id;
		this.latitude = r.latitude;
		this.longtitude = r.longtitude;
		this.altitude = r.altitude;
		this.Signal = r.Signal;
		this.SSID = r.SSID;
		this.mac = r.mac;
		this.frequncy = r.frequncy;
	}

	/**
	 * this function is to use the collection sort in the ArrayList
	 */
	public int compareTo(RowsRead a) {
		return (int)a.Signal - this.Signal;
	}

	/**
	 * toString function
	 */
	public String toString() {
		return date + "," + time + "," + id + "," + latitude + "," + longtitude + "," + altitude + "," + Signal + "," + SSID + "," + mac + "," + frequncy + System.lineSeparator();
	}

	/**
	 * toString function with head lines
	 */
	/*public String toString() {
		return "date: " + date + "," + "time: " + time + "," + "id: " + id + "," + "latitude: " + latitude + "," + "longtitude: " + longtitude + "," + "altitude: " + altitude + "," + "Signal: " + Signal + "," + "mac: " + mac + "," + "SSID: "+ SSID + "," + "frequncy: " + frequncy + System.lineSeparator();
	}*/

}