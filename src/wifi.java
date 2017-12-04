public class wifi {
	/**
	 * @author Rachel 
	 * 
	 * this class represents the wifi signals inside the finalRow
	 */
	
	int Signal, frequncy ;
	String SSID, mac;

	/**
	 * @param Signal
	 * @param mac
	 * @param SSID
	 * @param frequncy
	 * 
	 * the constructor
	 */
	public wifi(int Signal, String mac, String SSID, int frequncy) {
		this.Signal = Signal;
		this.mac = mac;
		this.SSID = SSID;
		this.frequncy = frequncy;

	}

	/**
	 * toString function
	 */
	public String toString() {
		return Signal + "," + SSID + "," + mac + "," + frequncy;
	}
}