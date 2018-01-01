import java.util.ArrayList;

public class FinalRow implements Comparable <FinalRow> {
	/**
	 *This class represents the final wifi rows we read 
	 *after filtering the 10 strongest
	 *
	 * @author Rachel
	 */

	String id,date,time;
	Point3D p;
	int numOfScans=0;
	ArrayList<wifi> wifis = new ArrayList<wifi>();

	/**
	 * the constructor
	 * 
	 * @param date
	 * @param time
	 * @param id
	 * @param latitude
	 * @param longtitude
	 * @param altitude
	 */
	public FinalRow( String date,String time, String id, double latitude, double longtitude, double altitude ) {

		this.date = date;
		this.time = time;
		this.id = id;
		this.p = new Point3D(longtitude, latitude, altitude);

	}
	/**
	 * the constructor for only one wifi
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
	public FinalRow( String date ,String time, String id, double latitude, double longtitude, double altitude, int Signal, String SSID,String mac , int frequncy) {

		this.date = date;
		this.time = time;
		this.id = id;
		this.p = new Point3D(longtitude, latitude, altitude);
		addline (Signal, mac, SSID, frequncy);

	}
	/**
	 * Copy constructor
	 * 
	 * @param r
	 */
	public FinalRow( FinalRow r) {

		this.date = r.date;
		this.time = r.time;
		this.id = r.id;	
		this.p = new Point3D(r.p.longtitude, r.p.latitude, r.p.altitude);
		addline (r.wifis.get(0).Signal, r.wifis.get(0).mac, r.wifis.get(0).SSID, r.wifis.get(0).frequncy);	
	}

	/**
	 * this sends the elements to the wifi constructor
	 * 
	 * @param Signal
	 * @param mac
	 * @param SSID
	 * @param frequncy
	 */
	public void addline(int Signal, String mac, String SSID, int frequncy) {

		this.wifis.add(new wifi(Signal,mac,SSID,frequncy));
		numOfScans++;
	}


	/**
	 * this function is to use the collection sort in the ArrayList
	 */
	public int compareTo(FinalRow a) {
		return (int)a.wifis.get(0).Signal - this.wifis.get(0).Signal;
	}


	/**
	 * toString function
	 * removes the '[' and the ']'chars from the wifi ArrayList
	 */
	public String toString() {
		String ans= date + " " + time + "," + id + "," + p + "," + numOfScans + "," + wifis.toString();
		//System.out.println(ans);
		for (int i = 0; i < ans.length(); i++){
			if (ans.charAt(i)=='[')
				ans=ans.substring(0, i) + ans.substring(i+1,ans.length()); 
			if (ans.charAt(i)==']')
				ans=ans.substring(0, i); 
			else
				if(ans.charAt(i)==',' && i+1<ans.length() && ans.charAt(i+1)==' ')
					ans=ans.substring(0, i+1) + ans.substring(i+2,ans.length());
		}
		if(ans.charAt(ans.length()-1)==',' )
			ans=ans.substring(0, ans.length()-1);
		return ans + System.lineSeparator();
	}
}
