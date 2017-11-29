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
	@SuppressWarnings("static-access")
	public static void readWrite () {

		ReaderForKml k=new ReaderForKml();

		k.readForKml(FILENAME);

		//TimeFilter t= new TimeFilter("2017-10-27 16:12:01");
		IdFilter id= new IdFilter("Lenovo PB2-690Y");
		//LocationFilter l =new LocationFilter(34.81328609, 32.16857397,38);
		//filter(k.Lines, t);
		filter(k.Lines, id);
		//filter(k.Lines, l);

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
