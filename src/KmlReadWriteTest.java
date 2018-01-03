import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

/**
 * class to check the KmlReadWrite class
 *
 *  @author Rachel
 */
public class KmlReadWriteTest {

	/**
	 * tester for time filter
	 */
	@Test
	public void timeFilterTest() {
		ArrayList<WifiScan> list1= new ArrayList<WifiScan>();
		ArrayList<WifiScan> list2= new ArrayList<WifiScan>();
		list1.add(new WifiScan("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list1.add(new WifiScan("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "DIRECT-Adfb","fc:7f:db:7e:70:v6" ,3));
		list1.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list1.add(new WifiScan("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "DIRECT-A5sfgn","fs:5f:db:7e:60:a6" ,6));
		list1.add(new WifiScan("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "DIRECT-A5xvb","fn:7f:db:7e:20:a6" ,9));
		list1.add(new WifiScan("27/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.742, 34.963, 39, -59, "DIRECT-A5sfgn","fu:9f:db:7e:25:a6" ,8));
		list1.add(new WifiScan("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "DIRECT-A5rtt","fk:1f:db:7e:09:a6" ,6));
		list1.add(new WifiScan("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "DIRECT-A5khs","fe:2f:db:7e:59:a6" ,36));
		list1.add(new WifiScan("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "DIRECT-A5dfnb","fc:1f:db:7e:72:a6" ,44));
		list1.add(new WifiScan("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "DIRECT-A5zcvvb","fg:8f:db:7e:52:a6" ,2));

		TimeFilter t= new TimeFilter("26/10/2017","15:50:20");
		KmlReadWrite.filter(list1, t);

		list2.add(new WifiScan("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list2.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));

		boolean flag=true;
		if (list1.size()==list2.size()){
			for (int i = 0; i < list1.size(); i++) {
				if(!list1.get(i).date.equals(list2.get(i).date) || 
						!list1.get(i).time.equals(list2.get(i).time) ||
						!list1.get(i).id.equals(list2.get(i).id) ||
						list1.get(i).p.longtitude!=list2.get(i).p.longtitude ||
						list1.get(i).p.latitude!=list2.get(i).p.latitude || 
						list1.get(i).p.altitude!=list2.get(i).p.altitude || 
						list1.get(i).wifis.get(0).Signal!=list2.get(i).wifis.get(0).Signal || 
						!list1.get(i).wifis.get(0).SSID.equals(list2.get(i).wifis.get(0).SSID) ||
						!list1.get(i).wifis.get(0).mac.equals(list2.get(i).wifis.get(0).mac) || 
						list1.get(i).wifis.get(0).frequncy!=list2.get(i).wifis.get(0).frequncy )	
					flag=false;				
			}
		}
		else 
			flag=false;
		assertTrue("Time filter error", flag);
	}

	/**
	 * tester for id filter
	 */
	@Test
	public void idFilterTest() {
		ArrayList<WifiScan> list1= new ArrayList<WifiScan>();
		ArrayList<WifiScan> list2= new ArrayList<WifiScan>();
		list1.add(new WifiScan("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list1.add(new WifiScan("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "DIRECT-Adfb","fc:7f:db:7e:70:v6" ,3));
		list1.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list1.add(new WifiScan("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "DIRECT-A5sfgn","fs:5f:db:7e:60:a6" ,6));
		list1.add(new WifiScan("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "DIRECT-A5xvb","fn:7f:db:7e:20:a6" ,9));
		list1.add(new WifiScan("27/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.742, 34.963, 39, -59, "DIRECT-A5sfgn","fu:9f:db:7e:25:a6" ,8));
		list1.add(new WifiScan("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "DIRECT-A5rtt","fk:1f:db:7e:09:a6" ,6));
		list1.add(new WifiScan("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "DIRECT-A5khs","fe:2f:db:7e:59:a6" ,36));
		list1.add(new WifiScan("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "DIRECT-A5dfnb","fc:1f:db:7e:72:a6" ,44));
		list1.add(new WifiScan("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "DIRECT-A5zcvvb","fg:8f:db:7e:52:a6" ,2));

		IdFilter id= new IdFilter("LG-H850");
		KmlReadWrite.filter(list1, id);

		list2.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list2.add(new WifiScan("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "DIRECT-A5xvb","fn:7f:db:7e:20:a6" ,9));
		list2.add(new WifiScan("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "DIRECT-A5khs","fe:2f:db:7e:59:a6" ,36));
		list2.add(new WifiScan("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "DIRECT-A5dfnb","fc:1f:db:7e:72:a6" ,44));
		list2.add(new WifiScan("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "DIRECT-A5zcvvb","fg:8f:db:7e:52:a6" ,2));

		boolean flag=true;
		if (list1.size()==list2.size()){
			for (int i = 0; i < list1.size(); i++) {
				if(!list1.get(i).date.equals(list2.get(i).date) || 
						!list1.get(i).time.equals(list2.get(i).time) ||
						!list1.get(i).id.equals(list2.get(i).id) ||
						list1.get(i).p.longtitude!=list2.get(i).p.longtitude ||
						list1.get(i).p.latitude!=list2.get(i).p.latitude || 
						list1.get(i).p.altitude!=list2.get(i).p.altitude || 
						list1.get(i).wifis.get(0).Signal!=list2.get(i).wifis.get(0).Signal || 
						!list1.get(i).wifis.get(0).SSID.equals(list2.get(i).wifis.get(0).SSID) ||
						!list1.get(i).wifis.get(0).mac.equals(list2.get(i).wifis.get(0).mac) || 
						list1.get(i).wifis.get(0).frequncy!=list2.get(i).wifis.get(0).frequncy )	
					flag=false;				
			}
		}
		else 
			flag=false;
		assertTrue("Id filter error", flag);

	}
	/**
	 * tester for location filter
	 */
	@Test
	public void locationFilterTest() {
		ArrayList<WifiScan> list1= new ArrayList<WifiScan>();
		ArrayList<WifiScan> list2= new ArrayList<WifiScan>();
		list1.add(new WifiScan("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list1.add(new WifiScan("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "DIRECT-Adfb","fc:7f:db:7e:70:v6" ,3));
		list1.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list1.add(new WifiScan("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "DIRECT-A5sfgn","fs:5f:db:7e:60:a6" ,6));
		list1.add(new WifiScan("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "DIRECT-A5xvb","fn:7f:db:7e:20:a6" ,9));
		list1.add(new WifiScan("27/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.742, 34.963, 39, -59, "DIRECT-A5sfgn","fu:9f:db:7e:25:a6" ,8));
		list1.add(new WifiScan("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "DIRECT-A5rtt","fk:1f:db:7e:09:a6" ,6));
		list1.add(new WifiScan("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "DIRECT-A5khs","fe:2f:db:7e:59:a6" ,36));
		list1.add(new WifiScan("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "DIRECT-A5dfnb","fc:1f:db:7e:72:a6" ,44));
		list1.add(new WifiScan("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "DIRECT-A5zcvvb","fg:8f:db:7e:52:a6" ,2));

		LocationFilter l= new LocationFilter(32.151,34.754);
		KmlReadWrite.filter(list1, l);

		list2.add(new WifiScan("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list2.add(new WifiScan("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "DIRECT-Adfb","fc:7f:db:7e:70:v6" ,3));
		list2.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list2.add(new WifiScan("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "DIRECT-A5sfgn","fs:5f:db:7e:60:a6" ,6));
		list2.add(new WifiScan("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "DIRECT-A5rtt","fk:1f:db:7e:09:a6" ,6));

		boolean flag=true;
		if (list1.size()==list2.size()){
			for (int i = 0; i < list1.size(); i++) {
				if(!list1.get(i).date.equals(list2.get(i).date) || 
						!list1.get(i).time.equals(list2.get(i).time) ||
						!list1.get(i).id.equals(list2.get(i).id) ||
						list1.get(i).p.longtitude!=list2.get(i).p.longtitude ||
						list1.get(i).p.latitude!=list2.get(i).p.latitude || 
						list1.get(i).p.altitude!=list2.get(i).p.altitude || 
						list1.get(i).wifis.get(0).Signal!=list2.get(i).wifis.get(0).Signal || 
						!list1.get(i).wifis.get(0).SSID.equals(list2.get(i).wifis.get(0).SSID) ||
						!list1.get(i).wifis.get(0).mac.equals(list2.get(i).wifis.get(0).mac) || 
						list1.get(i).wifis.get(0).frequncy!=list2.get(i).wifis.get(0).frequncy )	
					flag=false;				
			}
		}
		else 
			flag=false;
		assertTrue("Location filter error", flag);

	}
}
