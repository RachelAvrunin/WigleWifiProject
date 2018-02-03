package project;
import java.io.File;
import java.util.ArrayList;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import filters.*;
	/**
	 * this class responsible for sending the FILENAME to the reader -ReaderForkml
	 * and take the ArryList from it and create a Kml file with it
	 * 
	 * Source: kml created with the help of the site: https://labs.micromata.de/projects/jak/quickstart.html
	 * 
	 * @author Rachel
	 */
public class KmlReadWrite {

	private static final String FILENAME = "C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv";

	/**
	 * This function calls the ReaderForKml to read the file name 
	 * filters it with what we decide and enter
	 * and create the Kml file by adding the data to every dot 
	 */
	public void readWrite () {

		CombinedFileReader k=new CombinedFileReader();

		k.readAndSplit(FILENAME);

		activateFilter(k.Lines);

		k.deleteEquals();
		Write(k.Lines);

	}
	public void Write (ArrayList<WifiScan> lines) {
		try {

			// how to set multiply placemarks https://stackoverflow.com/questions/12701364/how-to-mark-multiple-coordinates-in-kml-using-java

			final Kml kml = new Kml();
			Document document= kml.createAndSetDocument().withName("MyMarkers");

			document.createAndSetTimeSpan().withBegin("2017-10-27T16:42:46Z").withEnd("2017-10-27T16:42:46Z");

			for (int i = 0; i < lines.size(); i++) {
				String str = lines.get(i).date+"T"+lines.get(i).time;
				str=str.replace("/", "-");

				Placemark pl = document.createAndAddPlacemark();
				pl.createAndSetTimeStamp().withWhen(str+"Z");
				pl.withName(lines.get(i).wifis.get(0).SSID).withOpen(Boolean.TRUE)
				.withDescription("id= "+lines.get(i).id+", signal= "+lines.get(i).wifis.get(0).Signal+", frequncy= "+lines.get(i).wifis.get(0).frequncy)
				.withAddress(lines.get(i).wifis.get(0).mac).createAndSetPoint()
				.addToCoordinates( lines.get(i).p.longtitude,lines.get(i).p.latitude,lines.get(i).p.altitude);
			}

			kml.marshal(new File("C:\\Users\\Rachel\\Downloads\\study\\OR\\WifiPlaces.kml"));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @param list
	 * 
	 * this function gets the ArrayList and uses the MyConsole to read an 
	 */
	private static void activateFilter(ArrayList<WifiScan> list) {
		int num=MyConsole.readInt("Insert a number for filtering: \n0 for no filter, 1 for Time, 2 for Id and 3 for Location radius \n");
		//String field;
		//double lon, lat,alt;
		while (num<0 || num>3)
			num=MyConsole.readInt("Not a valid input, please try again \n");

		switch (num){
		case 0:
			System.out.println("no filter has chosen");
			break;
		case 1:
			//field=MyConsole.readString("Insert the time you want");
			//TimeFilter t= new TimeFilter(field);
			TimeFilter t= new TimeFilter("2017-10-27","16:12:01");
			filter(list, t);
			System.out.println("TimeFilter done");
			break;
		case 2:
			//field=MyConsole.readString("Insert the id you want");
			//IdFilter id= new IdFilter(field);
			IdFilter id= new IdFilter("Lenovo PB2-690Y");
			filter(list, id);
			System.out.println("IdFilter done");
			break;
		case 3:
			//lon=MyConsole.readDouble("Insert a lon you want");
			//lat=MyConsole.readDouble("Insert a lat you want");
			//LocationFilter l =new LocationFilter(lon, lat);
			LocationFilter l =new LocationFilter(32.1608190337,34.8061381649);
			filter(list, l);
			System.out.println("LocationFilter done");
			break;
		default : 
			System.out.println("default not checked");
		}
	}

	/**
	 * @param list
	 * @param cond
	 * 
	 * this function send to the filter class to filter by the filter sent to the function
	 * and return the same list filtered 
	 */
	public static void filter(ArrayList<WifiScan> list, Filter cond){
		ArrayList<WifiScan> ans = new ArrayList<>();
		for(WifiScan r : list) {
			if(cond.test(r)) ans.add(r);
		}

		while (!list.isEmpty()) 
			list.remove(0);
		for (int i = 0; i < ans.size(); i++) {
			list.add(new WifiScan(ans.get(i)));
		}
	}

}
