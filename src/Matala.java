/**
 * only main for starting the program
 * 
 * @author Rachel
 */
public class Matala { 
	private static final String FILENAME1 = "C:\\Users\\Rachel\\Downloads\\study\\OR\\Ex2\\_BM2_comb_all_.csv";
	private static final String FILENAME2 = "C:\\Users\\Rachel\\Downloads\\study\\OR\\Ex2\\_comb_no_gps_ts1.csv";

	public static void main(String[] args) {

		ReadFolderWriteCsv.csv();
		KmlReadWrite.readWrite();
		MyLocation.location(FILENAME1,FILENAME2,4);
		routerLocation.location(FILENAME1,3);


	}

}
