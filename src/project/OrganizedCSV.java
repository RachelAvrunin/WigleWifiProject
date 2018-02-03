package project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This function gets ArrayList<wifiList>, and unite wifi's that have the same
 * date and coordinates to ArrayList of wifi.
 */
public class OrganizedCSV {

	public ArrayList<WifiScan> Orgenized(ArrayList<WifiScan> arr) {

		ArrayList<WifiScan> CSVList = new ArrayList<WifiScan>();
		WifiScan wifi = null;
		wifi sub = null;
		int j = 0;

		for (int i = 0; i < arr.size(); i++) {
			if (CSVList.size() == 0)
				CSVList.add(arr.get(0));
			else {
				wifi = arr.get(i);
				if (wifi.time.equals(CSVList.get(j).time)) {
					sub = wifi.wifis.get(0);
					CSVList.get(j).addline(sub);
				} else {
					Collections.sort(CSVList);
					CSVList.get(j).sort10();
					CSVList.add(arr.get(i));
					j = CSVList.size() - 1;
				}
			}
		}

		Collections.sort(CSVList);
		return CSVList;
	}

}