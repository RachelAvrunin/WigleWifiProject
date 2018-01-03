/**
 * only main for starting the program
 * 
 * @author Rachel
 */
public class Matala { 
	private static final String FILENAME = "C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv";

	public static void main(String[] args) {

		ReadFolderWriteCsv.csv();
	 	KmlReadWrite.readWrite();
		System.out.println(routerLocation.location(FILENAME, "2135as"));
	}

}
