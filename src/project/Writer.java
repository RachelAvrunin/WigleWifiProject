package project;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import filters.Filter;

/**
 * this class is responsible for writing the file
 * Source http://www.baeldung.com/java-write-to-file
 * 
 * @author Rachel
 */
public class Writer { 

	/**
	 * this function gets an ArrayList of WifiScan and write a file
	 * 
	 * @param lines
	 * @throws IOException
	 */
	public void csvWriter(ArrayList<WifiScan> lines , String filename) 
			throws IOException {
		//turn to String
		String stringForFile =turnToString(lines);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(stringForFile);
		writer.close();
	}
	
	/**
	 * this function gets a String and write a file
	 * 
	 * @param lines
	 * @throws IOException
	 */
	public void csvWriter(String lines , String filename) 
			throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(lines);
		writer.close();
	}
	
	/**
	 * the function gets an ArrayList of WifiScan and turns it to a String
	 * 
	 * @param lines
	 * @return
	 */
	public static  String turnToString(ArrayList<WifiScan> lines) {
		
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
	/**
	 * 
	 * @param WiFi
	 * @param loc
	 * @param name
	 */
	public void SortbyFilter(ArrayList<WifiScan> WiFi ,Filter loc , String name ){

		for(int i=0; i<WiFi.size(); i++){
			if(false == loc.test(WiFi.get(i))){
				WiFi.remove(i);
				i--;
			}

		}
		
		Writer wr = new Writer();
		try {
			wr.csvWriter(WiFi , name);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
