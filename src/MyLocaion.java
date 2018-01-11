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
	 * and a demedged  filename of combined file sends to read the file 
	 * send it one by one to find the location missing
	 * and calculate the estimated location of me
	 * 
	 * @param filename
	 * @param filenameToFix
	 * @param k
	 */
	public  static void location(String filename,String filenameToFix) {
		Writer d = new Writer();

		CombinedFileReader list=new CombinedFileReader();
		list.readAndNotSplit(filename);

		CombinedFileReader listToFix=new CombinedFileReader();
		listToFix.readAndNotSplit(filenameToFix);

		CombinedFileReader listForMac=new CombinedFileReader();
		listForMac.readAndNotSplit(filename);

		Point3D p;
		for (int i = 0; i < listToFix.Lines.size(); i++) {
			p=location(list.Lines, listToFix.Lines.get(i), listForMac.Lines);
			listToFix.Lines.get(i).setLocation(p);
		}
	 	String s = ReadFolderWriteCsv.turnToString(listToFix.Lines);
		try {

			d.csvWriter(s,"C:\\Users\\Rachel\\Downloads\\study\\OR\\fixedCsv.csv"); 
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
	public  static Point3D location(ArrayList<WifiScan> list, WifiScan f,ArrayList<WifiScan> listForMac) {
		WifiScan [] best = new WifiScan [4];
		double [] piOfBest = new double[4];
		double piOfLine = 1;
		double weight = 0;
		double dif = 0;
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
			if (piOfBest[0]<piOfLine){
				piOfBest[3]=piOfBest[2];
				best[3]=best[2];
				piOfBest[2]=piOfBest[1];
				best[2]=best[1];
				piOfBest[1]=piOfBest[0];
				best[1]=best[0];
				piOfBest[0]=piOfLine;
				best[0]=list.get(lineRead_i);
			}
			else
				if (piOfBest[1]<piOfLine){
					piOfBest[3]=piOfBest[2];
					best[3]=best[2];
					piOfBest[2]=piOfBest[1];
					best[2]=best[1];
					piOfBest[1]=piOfLine;
					best[1]=list.get(lineRead_i);

				}
				else
					if (piOfBest[2]<piOfLine){
						piOfBest[3]=piOfBest[2];
						best[3]=best[2];
						piOfBest[2]=piOfLine;
						best[2]=list.get(lineRead_i);
					}
					else
						if (piOfBest[3]<piOfLine){
							piOfBest[3]=piOfLine;
							best[3]=list.get(lineRead_i);
						}
			piOfLine=1;
		}

		Point3D[] points= new Point3D[fWifiSize];
		Point3D tempPoint= new Point3D (0,0,0);
		int index=0;
		for (int i = 0; i < fWifiSize; i++) {
			tempPoint=routerLocation.location(listForMac, f.wifis.get(i).mac);
			if (tempPoint!=null)
				points[index++]=tempPoint;
		}


		Point3D[] pointsWeight= new Point3D[index];
		for (int i = 0; i < pointsWeight.length ; i++)
			pointsWeight[i]=new Point3D(points[i].longtitude*piOfBest[i], points[i].latitude*piOfBest[i],points[i].altitude*piOfBest[i]);
		double longtitude = 0, latitude = 0, altitude = 0 ,piSum=0;
		for (int i = 0; i < pointsWeight.length; i++)
			if(pointsWeight[i]!=null){
				longtitude+=pointsWeight[i].longtitude;
				latitude+=pointsWeight[i].latitude;
				altitude+=pointsWeight[i].altitude;
				piSum+=piOfBest[i];
			}

		Point3D p=new Point3D(longtitude/piSum, latitude/piSum, altitude/piSum);
		return p;
	}
}
