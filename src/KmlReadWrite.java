import java.io.File;
import java.util.ArrayList;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;


// kml created with the help of the site: https://labs.micromata.de/projects/jak/quickstart.html
public class KmlReadWrite {


	@SuppressWarnings("static-access")
	public static void readWrite () {

		ReaderForKml k=new ReaderForKml();

		k.readForKml("C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv");

		//TimeFilter t= new TimeFilter("2017-10-27 16:12:01");
		//IdFilter id= new IdFilter("ONEPLUS A3003");
		//LocationFilter l =new LocationFilter(34.81328609, 32.16857397,38);
		//filter(k.Lines, t);
		//filter(k.Lines, id);
		//filter(k.Lines, l);

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
				.withId(k.Lines.get(i).id)
				.withDescription("signal="+k.Lines.get(i).Signal+", frequncy = "+k.Lines.get(i).frequncy)
				.withAddress(k.Lines.get(i).mac).createAndSetPoint()
				.addToCoordinates( k.Lines.get(i).longtitude,k.Lines.get(i).latitude,k.Lines.get(i).altitude);
			}

			kml.marshal(new File("C:\\Users\\Rachel\\Downloads\\study\\OR\\WifiPlaces.kml"));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}


	public static void filter(ArrayList<RowsRead> list, Filter cond){
		ArrayList<RowsRead> ans = new ArrayList<>();
		for(RowsRead r : list) {
			if(cond.test(r)) ans.add(r);
		}

		while (!list.isEmpty()) 
			list.remove(0);
		for (int i = 0; i < ans.size(); i++) {
			list.add(new RowsRead(
					ans.get(i).date,				//String date
					ans.get(i).time,				//String time
					ans.get(i).id,					//String id
					ans.get(i).latitude,			// double latitude
					ans.get(i).longtitude,			//double longtitude
					ans.get(i).altitude,			//double altitude
					ans.get(i).Signal,				//int Signal
					ans.get(i).SSID,				//String SSID
					ans.get(i).mac,					//String mac
					ans.get(i).frequncy)); 			//int frequncy
		}
	}

}
