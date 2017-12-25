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
public class ReaderForKml {


	ArrayList<RowsRead> Lines = new ArrayList<RowsRead>();

	/**
	 * @param filename
	 * 
	 * the function gets a file name in the format we created,  
	 * read the file and return ArrayList with what we need
	 */
	public void readForKml (String filename){
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

				for (int i = 6; i < splitString.length; i+=4) {

					Lines.add(new RowsRead(
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
	 * this function filter the SSID we have a few times by the strongest
	 */
	public void deleteEquals(){ 
		ArrayList<RowsRead> temp = new ArrayList<RowsRead>();
		ArrayList<RowsRead> ans = new ArrayList<RowsRead>();
		boolean flag=true;

		//Sort ArrayList by SSID
		Collections.sort(Lines,new Comparator<RowsRead>() {

			@Override
			public int compare(RowsRead o1, RowsRead o2) {
				return o2.SSID.compareTo(o1.SSID);
			}
		});

		String TempSSID;
		int index=0;
		while (!Lines.isEmpty()){
			TempSSID=Lines.get(index).SSID;
			while (flag && TempSSID.equals(Lines.get(index).SSID)){
				temp.add(new RowsRead(Lines.get(index)));
				index++;
				if (index>=Lines.size()){
					flag=false;
				}
			}
			for (int i = 0; i < index; i++) 
				Lines.remove(0);

			Collections.sort(temp);
			ans.add(new RowsRead(temp.get(0)));
			for (int i = 0; i <= temp.size(); i++) 
				temp.remove(0);
			index=0;

		}
		for (int i = 0; i < ans.size(); i++) {
			Lines.add(new RowsRead(ans.get(i)));
		}

	}

}
