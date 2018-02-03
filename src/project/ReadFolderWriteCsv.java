package project;
import java.io.IOException;

public class ReadFolderWriteCsv {

	/**
	 * this class responsible for sending the FOLDERNAME to the reader -ReaderForCsv  
	 *  and take the ArryList from it and send it to Writer
	 * 
	 * @author Rachel
	 */

	private static final String FOLDERNAME = "C:\\Users\\Rachel\\Downloads\\study\\OR\\data";

	/**
	 * This function calls the ReaderForCsv to read the folder name 
	 * insert it to a String and send it to the Writer
	 */

	public static void csv(){

		ReaderForCsv r=new ReaderForCsv();

		// call the read function
		r.readFolder(FOLDERNAME);
		writeCsv(r);

	}

	/*
	 * write the csv file
	 */
	private static void writeCsv(ReaderForCsv r) {
		Writer d = new Writer();


		// send to writer
		try {
			d.csvWriter(r.Lines,"C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv"); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} 
	}
}
