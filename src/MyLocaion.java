import java.io.IOException;
import java.util.ArrayList;

/**
 * the class is responsible for finding my location using routers around me 
 * 
 * @author Rachel
 */
public class MyLocaion {
	final static int NORM = 10000, POWER = 2, MIN_DIFF = 3, DIFF_NO_SIG = 100, NO_SIG = -120;
	final static double  SIG_DIFF = 0.4;
	/**
	 * the function takes a filename of combined file
	 * and a damaged  filename of combined file sends to read the file 
	 * send it one by one to find the location missing
	 * and calculate the estimated location of me
	 * and then send the whole file to the writer
	 * 
	 * @param filename
	 * @param filenameToFix
	 * @param k
	 */
	public  static void location(String filename,String filenameToFix,int k) {
		Writer d = new Writer();

		CombinedFileReader list=new CombinedFileReader();
		list.readAndNotSplit(filename);

		CombinedFileReader listToFix=new CombinedFileReader();
		listToFix.readAndNotSplit(filenameToFix);

		Point3D p;
		for (int i = 0; i < listToFix.Lines.size(); i++) {
			p=location(list.Lines , listToFix.Lines.get(i) , k);
			listToFix.Lines.get(i).setLocation(p);
		}
		String s = ReadFolderWriteCsv.turnToString(listToFix.Lines);
		try {

			d.csvWriter(s,"C:\\Users\\Rachel\\Downloads\\study\\OR\\fixedCsvAlgo2.csv"); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} 
	}

	/**
	 * the function takes an ArrayList of FinalRow
	 * and calculate the estimated location of me
	 * 
	 * @param list
	 * @return
	 */
	public  static Point3D location(ArrayList<WifiScan> list , WifiScan f , int k) {
		WifiScan [] best = new WifiScan [k];
		double [] piOfBest = new double[k];
		double piOfLine = 1;
		double weight = 0;
		double dif = 0;
		int index =0;
		boolean flag = false;
		int listSize, wifiSize, fWifiSize;
		listSize = list.size();
		fWifiSize = f.wifis.size();
		for (int lineRead_i = 0; lineRead_i < listSize; lineRead_i++) {
			wifiSize=list.get(lineRead_i).wifis.size();
			for (int macSearched = 0; macSearched < wifiSize; macSearched++) {
				for (int macOfLinef = 0; macOfLinef < fWifiSize; macOfLinef++) {
					if (list.get(lineRead_i).wifis.get(macSearched).mac.equals(f.wifis.get(macOfLinef).mac)){
						dif=Math.max(Math.abs(f.wifis.get(macOfLinef).Signal),MIN_DIFF);			
						flag=true;
						weight=NORM/(Math.pow(dif,SIG_DIFF)*Math.pow(f.wifis.get(macOfLinef).Signal,POWER));
					}
				}
				
				if(!flag)
					weight=NORM/(Math.pow(DIFF_NO_SIG,SIG_DIFF)*Math.pow(NO_SIG,POWER));
				piOfLine=piOfLine*weight;
				flag=false;
			}

			index=0;
			int num=Math.max(1, k-1);

			while (index<k)
				if (piOfBest[index]<piOfLine){
					if (index<num)
						for (int i = num; i > index; i--) {
							piOfBest[i]=piOfBest[i-1];
							best[i]=best[i--];
						}
					piOfBest[index]=piOfLine;
					best[index]=list.get(lineRead_i);
					index=k;
				}
				else
					index++;
			piOfLine=1;
		}

		index=0;
		for (int i = 0 ; i < k ; i++) {
			if (best[i]!=null)
				index++;
		}
		Point3D[] points= new Point3D[index];
		for (int i = 0 ; i < index ; i++) {
			points[i]=new Point3D(best[i].p.longtitude*piOfBest[i], best[i].p.latitude*piOfBest[i], best[i].p.altitude*piOfBest[i]);
		}

		double longtitude = 0, latitude = 0, altitude = 0 ,piSum=0;
		for (int i = 0; i < index; i++){
			longtitude+=points[i].longtitude;
			latitude+=points[i].latitude;
			altitude+=points[i].altitude;
			piSum+=piOfBest[i];
		}

		Point3D p=new Point3D(longtitude/piSum, latitude/piSum, altitude/piSum);
		return p;
	}
}
