
public class RowsRead implements Comparable <RowsRead> {

	String mac,SSID,date,time,id;
	int Signal, frequncy ;
	double longtitude,latitude, altitude;

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

	public int compareTo(RowsRead a) {
		return (int)a.Signal - this.Signal;
	}
	
	public String toString() {
		return date + "," + time + "," + id + "," + latitude + "," + longtitude + "," + altitude + "," + Signal + "," + SSID + "," + mac + "," + frequncy + System.lineSeparator();
	}
/*		public String toString() {
	return "date: " + date + "," + "time: " + time + "," + "id: " + id + "," + "latitude: " + latitude + "," + "longtitude: " + longtitude + "," + "altitude: " + altitude + "," + "Signal: " + Signal + "," + "mac: " + mac + "," + "SSID: "+ SSID + "," + "frequncy: " + frequncy + System.lineSeparator();
}*/

}
