import java.io.IOException;
//import java.util.Collections;


public class ReadWriteFile {

	/**
	 * @author Rachel
	 * 
	 * this class responsible for sending the FOLDERNAME to the Reader  
	 *  and taking the ArryList from i and send it to Writer
	 */

	private static final String FOLDERNAME = "C:\\Users\\Rachel\\Downloads\\study\\OR";

	@SuppressWarnings("static-access")
	public static void csv(){

		ReaderForCsv r=new ReaderForCsv();
		Writer d = new Writer();
		String stringForFile= "Date ,Time ,Id ,Latitude,Longtitude ,Altitude ,"
				+ "Signal1,SSID1,Mac1,frequncy1,"
				+ "Signal2,SSID2,Mac2,frequncy2,"
				+ "Signal3,SSID3,Mac3,frequncy3,"
				+ "Signal4,SSID4,Mac4,frequncy4,"
				+ "Signal5,SSID5,Mac5,frequncy5,"
				+ "Signal6,SSID6,Mac6,frequncy6,"
				+ "Signal7,SSID7,Mac7,frequncy7,"
				+ "Signal8,SSID8,Mac8,frequncy8,"
				+ "Signal9,SSID9,Mac9,frequncy9,"
				+ "Signal10,SSID10,Mac10,frequncy10,"
				+ System.lineSeparator();

		r.readFolder(FOLDERNAME);

		for (int i = 0; i < r.Lines.size(); i++)
			stringForFile=stringForFile+r.Lines.get(i).toString();



		try {
			// Write the file
			d.csvWriter(stringForFile); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} 

	}

}
