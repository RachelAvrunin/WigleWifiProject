package project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * this class only read files for the kml task
 * and contains an ArryList of RowsRead
 * 
 * @author Rachel
 */
public class CombinedFileReader {


	public ArrayList<WifiScan> Lines = new ArrayList<WifiScan>();

	/**
	 * @param filename
	 * 
	 * the function gets a file name in the format we created,  
	 * read the file and return ArrayList with what we need
	 */
	public void readAndSplit (String filename){
		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String splitString[]=new String [46];
			String splitDate[]=new String [2];

			// read first line and ignores it
			sCurrentLine =br.readLine();

			// read all the other lines until file is empty
			while ((sCurrentLine = br.readLine()) != null) {
				splitString=sCurrentLine.split(",");
				splitDate=splitString[0].split(" ");
				if (splitString[2].equals("?") || splitString[3].equals("?") || splitString[4].equals("?")){
					for (int i = 6; i < (Integer.parseInt(splitString[5])*4)+4; i+=4) {
						Lines.add(new WifiScan(
								splitDate[0],							//String date
								splitDate[1],							//String time
								splitString[1],							//String id
								Integer.parseInt(splitString[i+3]),		//int Signal
								splitString[i],							//String SSID
								splitString[i+1],						//String mac
								Integer.parseInt(splitString[i+2]))); 	//int frequncy
					}		
				}
				else{
					
					for (int i = 6; i < (Integer.parseInt(splitString[5])*4)+4 ; i+=4) {
						Lines.add(new WifiScan(
								splitDate[0],							//String date
								splitDate[1],							//String time
								splitString[1],							//String id
								Double.parseDouble(splitString[2]),		// double latitude
								Double.parseDouble(splitString[3]),		//double longtitude
								Double.parseDouble(splitString[4]),		//double altitude
								Integer.parseInt(splitString[i+3]),		//int Signal
								splitString[i],							//String SSID
								splitString[i+1],						//String mac
								Integer.parseInt(splitString[i+2]))); 	//int frequncy
					}
				}

			}
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

	/**
	 * the function gets a file name in the format we created,  
	 * read the file and return ArrayList with all the data
	 * 
	 * @param filename
	 */
	public void readAndNotSplit (String filename){
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String splitString[]=new String [46];
			String splitDate[]=new String [2];
			int size=-1;
			int len=0;

			// read first line and ignores it
			sCurrentLine =br.readLine();

			// read all the other lines until file is empty
			while ((sCurrentLine = br.readLine()) != null) {
				splitString=sCurrentLine.split(",");
				splitDate=splitString[0].split(" ");
				if (splitString[2].equals("?") || splitString[3].equals("?") || splitString[4].equals("?")){
					Lines.add(new WifiScan(
							splitDate[0],							//String date
							splitDate[1],							//String time
							splitString[1]));						//String id
					size++;

					for (int i = 6; i < splitString.length; i+=4)
						if(len<Integer.parseInt(splitString[5])) {
							Lines.get(size).addline(Integer.parseInt(splitString[i+3]),	//int Signal
									splitString[i+1],									//String mac 
									splitString[i],										//String SSID
									Integer.parseInt(splitString[i+2])); 				//int frequncy
							len++;
						}
					len=0;

				}
				else{
					Lines.add(new WifiScan(
							splitDate[0],							//String date
							splitDate[1],							//String time
							splitString[1],							//String id
							Double.parseDouble(splitString[2]),		// double latitude
							Double.parseDouble(splitString[3]),		//double longtitude
							Double.parseDouble(splitString[4])));	//double altitude
					size++;
					for (int i = 6; i < splitString.length; i+=4)
						if(len<Integer.parseInt(splitString[5])) {
							Lines.get(size).addline(Integer.parseInt(splitString[i+3]),	//int Signal
									splitString[i+1],									//String mac 
									splitString[i],										//String SSID
									Integer.parseInt(splitString[i+2])); 				//int frequncy
							len++;
						}
					len=0;
				}


			}
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
	public WifiScan ReadSample(String[] str){
		double Lat , Lon , Alt;
		WifiScan wifi = null;
		wifi sub = null;
		Point3D point = null;
		String MAC;
		int Signal;
		Lat = Double.parseDouble(str[2]);
		Lon = Double.parseDouble(str[3]);
		Alt = Double.parseDouble(str[4]);
		point = new Point3D(Lat, Lon, Alt);

		wifi = new WifiScan(null, null, null, point.latitude, point.longtitude, point.altitude);
		for(int i=0; i<str.length; i++){
			if(!((6+(i*4))==str.length)){
			MAC = str[7+(i*4)];
			Signal = (int)Double.parseDouble(str[9+(i*4)]);
			sub = new wifi( Signal, MAC,null, 0);
			wifi.addline(sub);
			}			
			else
				i = str.length;

		}
		
		return wifi;
		
	}
	
	/**
	 * this function filter the mac we have a few times by the strongest
	 */
	public void deleteEquals(){ 
		ArrayList<WifiScan> temp = new ArrayList<WifiScan>();
		ArrayList<WifiScan> ans = new ArrayList<WifiScan>();
		boolean flag=true;

		//Sort ArrayList by SSID
		Collections.sort(Lines,new Comparator<WifiScan>() {

			@Override
			public int compare(WifiScan o1, WifiScan o2) {
				return o2.wifis.get(0).mac.compareTo(o1.wifis.get(0).mac);
			}
		});

		String TempMac;
		int index=0;
		while (!Lines.isEmpty()){
			TempMac=Lines.get(index).wifis.get(0).mac;
			while (flag && TempMac.equals(Lines.get(index).wifis.get(0).mac)){
				temp.add(new WifiScan(Lines.get(index)));
				index++;
				if (index>=Lines.size()){
					flag=false;
				}
			}
			for (int i = 0; i < index; i++) 
				Lines.remove(0);

			Collections.sort(temp);
			ans.add(new WifiScan(temp.get(0)));
			for (int i = 0; i <= temp.size(); i++) 
				temp.remove(0);
			index=0;

		}
		for (int i = 0; i < ans.size(); i++) {
			Lines.add(new WifiScan(ans.get(i)));
		}

	}

}
