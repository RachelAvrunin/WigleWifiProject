import java.io.File;
import java.util.ArrayList;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class KmlReadWrite {
	/**
	 * @author Rachel
	 * 
	 * this class responsible for sending the FILENAME to the reader -ReaderForkml
	 * and take the ArryList from it and create a Kml file with it
	 * 
	 * Source: kml created with the help of the site: https://labs.micromata.de/projects/jak/quickstart.html
	 */
	private static final String FILENAME = "C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv";

	/**
	 * This function calls the ReaderForKml to read the file name 
	 * filters it with what we decide and enter
	 * and create the Kml file by adding the data to every dot 
	 */
	public static void readWrite () {

		ReaderForKml k=new ReaderForKml();

		k.readForKml(FILENAME);

		activateFilter(k.Lines);

		k.deleteEquals();

		try {

			// how to set multiply placemarks https://stackoverflow.com/questions/12701364/how-to-mark-multiple-coordinates-in-kml-using-java

			final Kml kml = new Kml();
			Document document= kml.createAndSetDocument().withName("MyMarkers");

			document.createAndSetTimeSpan().withBegin("2017-10-27T16:42:46Z").withEnd("2017-10-27T16:42:46Z");

			for (int i = 0; i < k.Lines.size(); i++) {
				String str = k.Lines.get(i).date+"T"+k.Lines.get(i).time;
				str=str.replace("/", "-");

				Placemark pl = document.createAndAddPlacemark();
				pl.createAndSetTimeStamp().withWhen(str+"Z");
				pl.withName(k.Lines.get(i).SSID).withOpen(Boolean.TRUE)
				.withDescription("id= "+k.Lines.get(i).id+", signal= "+k.Lines.get(i).Signal+", frequncy= "+k.Lines.get(i).frequncy)
				.withAddress(k.Lines.get(i).mac).createAndSetPoint()
				.addToCoordinates( k.Lines.get(i).longtitude,k.Lines.get(i).latitude,k.Lines.get(i).altitude);
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
	private static void activateFilter(ArrayList<RowsRead> list) {
		int num=MyConsole.readInt("Insert a number for filtering: \n0 for no filter, 1 for Time, 2 for Id and 3 for Location radius \n");
		//String field;
		//double lon, lat,alt;
		while (num<0 || num>3)
			num=MyConsole.readInt("Not a valid input, please try again \n");

		switch (num){
		case 0:
			System.out.println("no fiolter has chosen");
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
			LocationFilter l =new LocationFilter(32.16690033,34.80890703);
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
	public static void filter(ArrayList<RowsRead> list, Filter cond){
		ArrayList<RowsRead> ans = new ArrayList<>();
		for(RowsRead r : list) {
			if(cond.test(r)) ans.add(r);
		}

		while (!list.isEmpty()) 
			list.remove(0);
		for (int i = 0; i < ans.size(); i++) {
			list.add(new RowsRead(ans.get(i)));
		}
	}

}
