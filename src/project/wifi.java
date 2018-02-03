package project;
public class wifi {
	/**
	 * this class represents the wifi signals inside the finalRow
	 * 
	 * @author Rachel 
	 */

	public int Signal ;
	public int frequncy;
	public String SSID, mac;

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
 * The copy constructer
 * 
 * @param w
 */
	public wifi(wifi w) {
		this.Signal = w.Signal;
		this.mac = w.mac;
		this.SSID = w.SSID;
		this.frequncy = w.frequncy;

	}
	/**
	 * toString function
	 */
	public String toString() {
		return SSID + "," + mac + "," + frequncy + "," + Signal;
	}
}