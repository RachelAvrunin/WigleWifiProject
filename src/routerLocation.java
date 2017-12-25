import java.util.ArrayList;
import java.util.Collections;

/**
 * the class is responsible for finding a router location of a router 
 * by an ArrayList of scans
 * 
 * @author Rachel
 */
public class routerLocation {

	/**
	 * the function takes an ArrayLis of RowsRead and a mac address
	 * and calculate the estimated location of the router 
	 *
	 * @param list
	 * @param macToFind
	 * @return
	 */
	public  static Point3D location(ArrayList<RowsRead> list, String macToFind) {
		ArrayList<RowsRead> hasTheMac= new ArrayList<RowsRead>();
		for (int i = 0; i <list.size(); i++) 
			if (list.get(i).mac.equals(macToFind))
				hasTheMac.add(list.get(i));
		Collections.sort(hasTheMac);

		int num =Math.min(hasTheMac.size(),3);
		double [][] weight=new double[num+1][4];
		for (int i = 0; i < num; i++) {
			setWeight( hasTheMac.get(i), weight[i]);
		}
		if (num!=0){
			for (int i = 0; i < weight.length; i++) {
				weight[num][0]+=weight[i][0];
				weight[num][1]+=weight[i][1];
				weight[num][2]+=weight[i][2];
				weight[num][3]+=weight[i][3];
			}
			Point3D p=new Point3D(weight[num][1]/weight[num][0],weight[num][2]/weight[num][0], weight[num][3]/weight[num][0]);
			return p;
		}
		else 
			return null;
	}

	/**
	 * the function get a row and calculate the weight of the line
	 * 
	 * @param r
	 * @param weight
	 */
	public static void setWeight(RowsRead r, double [] weightLine){
		weightLine[0]=1/(double)(r.Signal*r.Signal);			// 1/signal^2
		weightLine[1]=r.longtitude*weightLine[0];		// lon*weight
		weightLine[2]=r.latitude*weightLine[0];			// lat*weight
		weightLine[3]=r.altitude*weightLine[0];			// alt*weight
	}
}
