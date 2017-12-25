public class wifi {
	/**
	 * this class represents the wifi signals inside the finalRow
	 * 
	 * @author Rachel 
	 */

	int Signal, frequncy ;
	String SSID, mac;

	/**
	 * the constructor
	 * 
	 * @param Signal
	 * @param mac
	 * @param SSID
	 * @param frequncy
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
		return SSID + "," + mac + "," + frequncy + "," + Signal;
	}
}