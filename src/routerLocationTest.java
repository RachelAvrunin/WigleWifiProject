import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

/**
 * a class to check the routerLocation class
 * 
 * @author Rachel
 */
public class routerLocationTest {

	/**
	 * a function to check if the location returned is accurate for a mixed list
	 */
	@Test
	public void locationTest1() {
		ArrayList<RowsRead> list1= new ArrayList<RowsRead>();
		list1.add(new RowsRead("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list1.add(new RowsRead("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "DIRECT-Adfb","fc:7f:db:7e:70:v6" ,3));
		list1.add(new RowsRead("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list1.add(new RowsRead("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "DIRECT-A5sfgn","fs:5f:db:7e:60:a6" ,6));
		list1.add(new RowsRead("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "DIRECT-A5xvb","fn:7f:db:7e:20:a6" ,9));
		list1.add(new RowsRead("27/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.742, 34.963, 39, -59, "DIRECT-A5sfgn","fc:4f:db:7e:50:a6" ,8));
		list1.add(new RowsRead("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "DIRECT-A5rtt","fc:4f:db:7e:50:a6" ,6));
		list1.add(new RowsRead("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "DIRECT-A5khs","fe:2f:db:7e:59:a6" ,36));
		list1.add(new RowsRead("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "DIRECT-A5dfnb","fc:4f:db:7e:50:a6" ,44));
		list1.add(new RowsRead("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "DIRECT-A5zcvvb","fg:8f:db:7e:52:a6" ,2));

		Point3D ans=routerLocation.location(list1,"fc:4f:db:7e:50:a6");
		Point3D p= new Point3D(34.803868237976296, 32.34244914757011, 33.514710073257);

		assertTrue("router location error", ans.longtitude==p.longtitude && ans.latitude==p.latitude && ans.altitude==p.altitude);

	}

	/**
	 * a function to check if the location returned is accurate for a list with one match
	 */
	@Test
	public void locationTest2() {
		ArrayList<RowsRead> list1= new ArrayList<RowsRead>();
		list1.add(new RowsRead("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DIRECT-A5-Hcvb","fc:4f:db:7e:50:a6" ,1));
		list1.add(new RowsRead("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "DIRECT-Adfb","fc:7f:db:7e:70:v6" ,3));
		list1.add(new RowsRead("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DIRECT-sAsfgn5-HP","ff:3f:db:7e:60:a6" ,14));
		list1.add(new RowsRead("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "DIRECT-A5sfgn","fs:5f:db:7e:60:a6" ,6));
		list1.add(new RowsRead("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "DIRECT-A5xvb","fn:7f:db:7e:20:a6" ,9));
		list1.add(new RowsRead("27/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.742, 34.963, 39, -59, "DIRECT-A5sfgn","fc:4f:db:7e:50:a6" ,8));
		list1.add(new RowsRead("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "DIRECT-A5rtt","fc:4f:db:7e:50:a6" ,6));
		list1.add(new RowsRead("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "DIRECT-A5khs","fe:2f:db:7e:59:a6" ,36));
		list1.add(new RowsRead("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "DIRECT-A5dfnb","fc:4f:db:7e:50:a6" ,44));
		list1.add(new RowsRead("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "DIRECT-A5zcvvb","fg:8f:db:7e:52:a6" ,2));

		Point3D ans=routerLocation.location(list1,"fn:7f:db:7e:20:a6");
		Point3D p= new Point3D(34.411, 32.417, 52.99999999999999);

		assertTrue("router location with 1 mac exist error", ans.longtitude==p.longtitude && ans.latitude==p.latitude && ans.altitude==p.altitude);
	}
}