package project;
/**
 * only main for starting the program
 * 
 * @author Rachel
 */
public class Main { 
	private static final String FILENAME1 = "C:\\Users\\Rachel\\Downloads\\study\\OR\\data\\Ex2\\_BM2_comb_all_.csv";
	private static final String FILENAME2 = "C:\\Users\\Rachel\\Downloads\\study\\OR\\data\\Ex2\\_comb_no_gps_ts1.csv";

	public static void main(String[] args) {

		KmlReadWrite kml = new KmlReadWrite();

		ReadFolderWriteCsv.csv();
		kml.readWrite();
		MyLocation.location(FILENAME1,FILENAME2,4);
		routerLocation.location(FILENAME1,3);


	}

}
