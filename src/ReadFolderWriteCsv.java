import java.io.IOException;
import java.util.ArrayList;

public class ReadFolderWriteCsv {

	/**
	 * this class responsible for sending the FOLDERNAME to the reader -ReaderForCsv  
	 *  and take the ArryList from it and send it to Writer
	 * 
	 * @author Rachel
	 */

	private static final String FOLDERNAME = "C:\\Users\\Rachel\\Downloads\\study\\OR";

	/**
	 * This function calls the ReaderForCsv to read the folder name 
	 * insert it to a String and send it to the Writer
	 */
	@SuppressWarnings("static-access")
	public static void csv(){

		ReaderForCsv r=new ReaderForCsv();
		Writer d = new Writer();
		
		// call the read function
		r.readFolder(FOLDERNAME);

		//turn to String
		String stringForFile = turnToString(r.Lines);

		// send to writer
		try {

			d.csvWriter(stringForFile); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} 

	}
/**
 * the function gets an ArrayList of WifiScan and turns it to a String
 * 
 * @param lines
 * @return
 */
	private static  String turnToString(ArrayList<WifiScan> lines) {
		// insert headlines
		String stringForFile= "Date,Id,Latitude,Longtitude,Altitude,WiFi networks,"
				+ "SSID1,Mac1,frequncy1,Signal1,"
				+ "SSID2,Mac2,frequncy2,Signal2,"
				+ "SSID3,Mac3,frequncy3,Signal3,"
				+ "SSID4,Mac4,frequncy4,Signal4,"
				+ "SSID5,Mac5,frequncy5,Signal5,"
				+ "SSID6,Mac6,frequncy6,Signal6,"
				+ "SSID7,Mac7,frequncy7,Signal7,"
				+ "SSID8,Mac8,frequncy8,Signal8,"
				+ "SSID9,Mac9,frequncy9,Signal9,"
				+ "SSID10,Mac10,frequncy10,Signal10,"
				+ System.lineSeparator();

		// insert the ArrayList to the String
		for (int i = 0; i < lines.size(); i++)
			stringForFile=stringForFile+lines.get(i).toString();
		
		return stringForFile;
	}

}
