import java.util.ArrayList;

public class FinalRow{

	String id,date,time;
	double longtitude,latitude, altitude;
	ArrayList<wifi> wifis = new ArrayList<wifi>();


	public FinalRow( String date,String time, String id, double latitude, double longtitude, double altitude ) {

		this.date = date;
		this.time = time;
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.altitude = altitude;

	}

	public void addline(int Signal, String mac, String SSID, int frequncy) {

		this.wifis.add(new wifi(Signal,mac,SSID,frequncy));
	}

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
