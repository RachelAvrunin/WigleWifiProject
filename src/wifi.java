public class wifi {

	int Signal, frequncy ;
	String SSID, mac;

	public wifi(int Signal, String mac, String SSID, int frequncy) {
		this.Signal = Signal;
		this.mac = mac;
		this.SSID = SSID;
		this.frequncy = frequncy;

	}
	
	public void addWifi(int Signal, String mac, String SSID, int frequncy) {
		this.Signal = Signal;
		this.mac = mac;
		this.SSID = SSID;
		this.frequncy = frequncy;

	}
	/*
  	public wifi(Rows row) {

		this.mac = row.mac;
		this.SSID = row.SSID;
		this.time = row.time;
		this.Signal = row.Signal;
		this.frequncy = row.frequncy;
		this.longtitude = row.longtitude;
		this.latitude = row.latitude;
		this.altitude = row.altitude;
		this.id = row.id;
		//stem.out.println(time);
	}

	public String toString() {
		return mac + "," + SSID + "," + time + "," + id + "," + Signal + "," + frequncy + "," + longtitude + "," + latitude + "," + altitude + System.lineSeparator();
	}

	public int compareTo(wifi a) {
		return (int)a.Signal - this.Signal;

	}*/

	
	public String toString() {
		return Signal + "," + SSID + "," + mac + "," + frequncy;
	}
}


