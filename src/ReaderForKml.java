import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collections;

/**
 * @author Rachel
 * 
 * this class only read files for the kml task
 * and contains an ArryList of Rows
 * 
 */
public class ReaderForKml {
	static ArrayList<RowsRead> Lines = new ArrayList<RowsRead>();

	/**
	 * @param filename
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
			//int index=0;

			// first line 
			sCurrentLine =br.readLine();

			// all the other lines until file is empty
			while ((sCurrentLine = br.readLine()) != null) {
				splitString=sCurrentLine.split(",");
				for (int i = 6; i < splitString.length; i+=4) {
					if(splitString[i].contains(" "))
						splitString[i]=splitString[i].substring(1,splitString[i].length());
					Lines.add(new RowsRead(
							splitString[0],							//String date
							splitString[1],							//String time
							splitString[2],							//String id
							Double.parseDouble(splitString[3]),		// double latitude
							Double.parseDouble(splitString[4]),		//double longtitude
							Double.parseDouble(splitString[5]),		//double altitude
							Integer.parseInt(splitString[i]),		//int Signal
							splitString[i+1],							//String SSID
							splitString[i+2],							//String mac
							Integer.parseInt(splitString[i+3]))); 	//int frequncy
					//System.out.print(Lines.get(index));
					//index++;

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

}
