package project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class ReaderForCsv {
	/**
	 * this class only read folders and files for the csv task
	 * and contains an ArryList of FinalRow
	 * 
	 * @author Rachel
	 */

	public ArrayList<WifiScan> Lines = new ArrayList<WifiScan>();
	private static int index=0;
	/**
	 * This function gets String folder name and send to the function check each file from the folder
	 * 
	 * @param wiggleDir
	 */
	public void readFolder(File wiggleDir) {

		Path p= Paths.get(wiggleDir.getPath());

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
	 * This function gets String folder name and send to the function check each file from the folder
	 * 
	 * @param foldername
	 */
	public void readFolder(String foldername) {

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
	 * the function gets file names and check them if they are in the right format 
	 * if they are it sends it to the function read
	 * 
	 * @param file
	 */
	public void check(String file){

		if (file.contains("WigleWifi") && file.contains(".csv"))
			read(file);

	}

	/***
	 * the function gets file names from the function check
	 * read the file take only the strongest networks and return ArrayList of FinalRows
	 * 
	 * Source https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
	 * 
	 * @param filename
	 */
	public void read (String filename){

		BufferedReader br = null;
		FileReader fr = null;

		try {
			ArrayList<WifiScan> temp = new ArrayList<WifiScan>();

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
						temp.add(new WifiScan(
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
					Lines.add(new WifiScan(
							temp.get(0).date,				//String date
							temp.get(0).time,				//String time
							temp.get(0).id,					//String id
							temp.get(0).p.latitude,			// double latitude
							temp.get(0).p.longtitude,			//double longtitude
							temp.get(0).p.altitude));			//double altitude

					for (int i = 0 ; i < Math.min(10,temp.size()) ; i++) 
						Lines.get(index).addline(
								temp.get(i).wifis.get(0).Signal,			//int Signal
								temp.get(i).wifis.get(0).mac,			//String mac
								temp.get(i).wifis.get(0).SSID,			//String SSID
								temp.get(i).wifis.get(0).frequncy);		//int frequncy
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

	public ReaderForCsv Merge(ReaderForCsv dataBase, ReaderForCsv temp) {
		ReaderForCsv ans= new ReaderForCsv();
		for (int i = 0; i < dataBase.Lines.size(); i++) {
			ans.Lines.add(dataBase.Lines.get(i));
		}		
		for (int i = 0; i < temp.Lines.size(); i++) {
			ans.Lines.add(temp.Lines.get(i));
		}	
		return ans;
	}
}
