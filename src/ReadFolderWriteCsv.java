import java.io.IOException;

public class ReadFolderWriteCsv {

	/**
	 * @author Rachel
	 * 
	 * this class responsible for sending the FOLDERNAME to the reader -ReaderForCsv  
	 *  and take the ArryList from it and send it to Writer
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
		
		// insert headlines
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

		// call the read function
		r.readFolder(FOLDERNAME);

		// insert the ArrayList to a String
		for (int i = 0; i < r.Lines.size(); i++)
			stringForFile=stringForFile+r.Lines.get(i).toString();

		// send to writer
		try {

			d.csvWriter(stringForFile); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} 

	}

}
