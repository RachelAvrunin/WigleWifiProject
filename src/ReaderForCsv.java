import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class ReaderForCsv {
	/**
	 * @author Rachel
	 * 
	 * this class only read folders and files for the csv task
	 * and contains an ArryList of FinalRow
	 */

	static ArrayList<FinalRow> Lines = new ArrayList<FinalRow>();
	private static int index=0;

	/**
	 * @param foldername
	 * 
	 * This function gets String folder name and send to the function check each file from the folder
	 */
	public static void readFolder(String foldername) {

		Path p= Paths.get(foldername);

		try {

			Files.newDirectoryStream(p).forEach(
					file -> check(file.toString())
					);

		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @param file
	 * 
	 * the function gets file names and check them if they are in the right format 
	 * if they are it sends it to the function read
	 */
	public static void check(String file){

		if (file.contains("WigleWifi") && file.contains(".csv"))
			read(file);

	}

	/***
	 * @param filename
	 * 
	 * the function gets file names from the function check
	 * read the file take only the strongest networks and return ArrayList of FinalRows
	 * 
	 * Source https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
	 */
	public static void read (String filename){

		BufferedReader br = null;
		FileReader fr = null;

		try {
			ArrayList<RowsRead> temp = new ArrayList<RowsRead>();

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String splitString[]=new String [11];
			String id, tempTime;
			String splitDate[]=new String [2];


			// read first line and takes the id of the file
			sCurrentLine =br.readLine();
			splitString=sCurrentLine.split(",");
			id=splitString[2];
			int indexForId = id.indexOf("=");
			id = id.substring(indexForId+1, id.length() );

			// read second line and ignores it
			sCurrentLine =br.readLine();


			// read all the other lines until file is empty
			sCurrentLine = br.readLine();	
			splitString=sCurrentLine.split(",");

			do {				
				tempTime=splitString[3];
				splitDate=splitString[3].split(" ");

				while (tempTime.equals(splitString[3]) && sCurrentLine!=null) {

					if (splitString[10].equals("WIFI")){	// if the line is wifi & not gsm add line
						temp.add(new RowsRead(
								splitDate[0],							//String date
								splitDate[1],							//String time
								id,										//String id
								Double.parseDouble(splitString[6]),		// double latitude
								Double.parseDouble(splitString[7]),		//double longtitude
								Double.parseDouble(splitString[8]),		//double altitude
								Integer.parseInt(splitString[5]),		//int Signal
								splitString[1],							//String SSID
								splitString[0],							//String mac
								Integer.parseInt(splitString[4]))); 	//int frequncy
					}

					sCurrentLine = br.readLine();

					if  (sCurrentLine!=null){
						splitString=sCurrentLine.split(",");
						splitDate=splitString[3].split(" ");
					}
				} 
				Collections.sort(temp);

				if  (!temp.isEmpty()){
					Lines.add(new FinalRow(
							temp.get(0).date,				//String date
							temp.get(0).time,				//String time
							temp.get(0).id,					//String id
							temp.get(0).latitude,			// double latitude
							temp.get(0).longtitude,			//double longtitude
							temp.get(0).altitude));			//double altitude

					for (int i = 0 ; i < Math.min(10,temp.size()) ; i++) 
						Lines.get(index).addline(
								temp.get(i).Signal,			//int Signal
								temp.get(i).mac,			//String mac
								temp.get(i).SSID,			//String SSID
								temp.get(i).frequncy);		//int frequncy
					index++;
				}
				while (!temp.isEmpty()) 
					temp.remove(0);
			}
			while (sCurrentLine != null);

		}
		catch (IOException e) {

			e.printStackTrace();
		}
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
			}
			catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}
}
