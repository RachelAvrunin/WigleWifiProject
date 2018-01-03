import static org.junit.Assert.*;
import org.junit.Test;
/**
 * class to check the ReaderForKml class
 * 
 * @author Rachel
 */
public class CombinedFileReaderTest {
	
/**
 * tester for the deleteEquals function
 */
	@Test
	public void deleteEqualstest() {

		CombinedFileReader list1= new CombinedFileReader();
		CombinedFileReader list2= new CombinedFileReader();

		list1.Lines.add(new WifiScan("26/10/2017" ,"15:50:20", "Lenovo PB2-690Y",32.152, 34.751, 40, -70, "DD123","fc:4f:db:7e:50:a6" ,1));
		list1.Lines.add(new WifiScan("26/10/2017" ,"16:35:20", "Lenovo PB2-690Y",32.151, 34.755, 40, -80, "AA333","fc:7f:db:7e:70:v6" ,3));
		list1.Lines.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DD123","ff:3f:db:7e:60:a6" ,14));
		list1.Lines.add(new WifiScan("26/10/2017" ,"16:16:31", "Lenovo PB2-690Y",32.155, 34.755, 41, -69, "AA333","fs:5f:db:7e:60:a6" ,6));
		list1.Lines.add(new WifiScan("27/10/2017" ,"16:16:35", "LG-H850",32.417, 34.411, 53, -70, "CC1414","fn:7f:db:7e:20:a6" ,9));
		list1.Lines.add(new WifiScan("27/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.742, 34.963, 39, -59, "DD123","fu:9f:db:7e:25:a6" ,8));
		list1.Lines.add(new WifiScan("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "AA333","fk:1f:db:7e:09:a6" ,6));
		list1.Lines.add(new WifiScan("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "CC1414","fe:2f:db:7e:59:a6" ,36));
		list1.Lines.add(new WifiScan("28/10/2017" ,"18:34:15", "LG-H850",32.369, 34.756, 38, -60, "CC1414","fc:1f:db:7e:72:a6" ,44));
		list1.Lines.add(new WifiScan("28/10/2017" ,"18:00:17", "LG-H850",32.147, 34.741, 40, -72, "CC1414","fg:8f:db:7e:52:a6" ,2));

		list1.deleteEquals();
		
		list2.Lines.add(new WifiScan("26/10/2017" ,"15:50:20", "LG-H850",32.149, 34.752, 42, -59, "DD123","ff:3f:db:7e:60:a6" ,14));
		list2.Lines.add(new WifiScan("28/10/2017" ,"17:50:20", "LG-H850",32.258, 34.257, 41, -50, "CC1414","fe:2f:db:7e:59:a6" ,36));
		list2.Lines.add(new WifiScan("26/10/2017" ,"12:34:30", "Lenovo PB2-690Y",32.147, 34.752, 29, -40, "AA333","fk:1f:db:7e:09:a6" ,6));


		boolean flag=true;
		if (list1.Lines.size()==list2.Lines.size()){
			for (int i = 0; i < list1.Lines.size(); i++) {
				if(!list1.Lines.get(i).date.equals(list2.Lines.get(i).date) || 
						!list1.Lines.get(i).time.equals(list2.Lines.get(i).time) ||
						!list1.Lines.get(i).id.equals(list2.Lines.get(i).id) ||
						list1.Lines.get(i).p.longtitude!=list2.Lines.get(i).p.longtitude ||
						list1.Lines.get(i).p.latitude!=list2.Lines.get(i).p.latitude || 
						list1.Lines.get(i).p.altitude!=list2.Lines.get(i).p.altitude || 
						list1.Lines.get(i).wifis.get(0).Signal!=list2.Lines.get(i).wifis.get(0).Signal || 
						!list1.Lines.get(i).wifis.get(0).SSID.equals(list2.Lines.get(i).wifis.get(0).SSID) ||
						!list1.Lines.get(i).wifis.get(0).mac.equals(list2.Lines.get(i).wifis.get(0).mac) || 
						list1.Lines.get(i).wifis.get(0).frequncy!=list2.Lines.get(i).wifis.get(0).frequncy )	
					flag=false;				
			}
		}
		else 
			flag=false;
		
		assertTrue("delete equals eror", flag);
	}

}
