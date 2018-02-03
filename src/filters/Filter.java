package filters;

import project.WifiScan;

/**
 * this interface is for filtering the ArrayList for the Kml files
 * it help's us by no needing a full class for each filter type  
 * 
 * @author Rachel
 */
public interface Filter {
	boolean test (WifiScan item);

}