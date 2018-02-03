package GUI;
import java.io.File;
import java.io.IOException;
import project.*;




public class Link {

	ReaderForCsv DataBase = new ReaderForCsv();
	ReaderForCsv temp = new ReaderForCsv();

	public void ReadDir(File wiggleDir){

		//	OrganizedCSV org = new OrganizedCSV();
		DataBase.readFolder(wiggleDir);
		//	DataBase = org.Orgenized(DataBase);
	}

	public void ReadCSV(File csv , boolean flag){
		ReaderForCsv read = new ReaderForCsv();
		if(flag){
			temp.readFolder(csv);
			DataBase = read.Merge(DataBase, temp);
		}
		else
			DataBase.read(csv.getPath()); 
	}


	public void SaveKML(){
		KmlReadWrite kml = new KmlReadWrite();
		kml.Write(DataBase.Lines);

	}

	public void SaveCSV(String name){
		Writer d= new Writer();
		try {
			d.csvWriter(DataBase.Lines, name);
		}
		catch (IOException e) {
			e.printStackTrace();
		}


	}


	  public String[] Algo1(String mac , int num){
		Point3D p = routerLocation.location(DataBase.Lines, mac ,num);
		String str1="";
		if (p!=null)
			str1= p.toString();
		else 
			str1= "0,0,0";
		String[] str2 = str1.split(",");
		return str2;
	}

	public String[] Algo2Sample(String[] str){
		CombinedFileReader rd = new CombinedFileReader();
		WifiScan wifi = rd.ReadSample(str);
		wifi.p = MyLocation.location(DataBase.Lines, wifi, 3);
		String[] res = wifi.p.toString().split(",");
		return res;
	}

	public String[] Algo2Pairs(String[] str){
		Point3D point = new Point3D(0, 0, 0);
		WifiScan wifi = new WifiScan(null, null, null, point.latitude, point.longtitude, point.altitude);

		wifi sub1 , sub2 , sub3;

		sub1 = new wifi(Integer.parseInt(str[1]), str[2], null,  0);
		sub2 = new wifi(Integer.parseInt(str[3]) , str[2] , null, 0);
		sub3 = new wifi(  Integer.parseInt(str[5]) , str[4] , null, 0);
		wifi.addline(sub1);
		wifi.addline(sub2);
		wifi.addline(sub3);
		wifi.p = MyLocation.location(DataBase.Lines, wifi, 3);
		String[] res = wifi.p.toString().split(",");
		return res;
	}

}