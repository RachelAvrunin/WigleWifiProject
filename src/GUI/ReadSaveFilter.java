package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import filters.Filter;

public class ReadSaveFilter {

	public void SaveFilter(Filter fl) {



		try {
			FileOutputStream f = new FileOutputStream(new File("Filter.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(fl);

			o.close();
			f.close();


		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}

	}

	
	public Filter ReadFilter(){
		try{
		Open op = new Open();
		File file = op.PickFilterFile();
		FileInputStream fi = new FileInputStream(new File(file.getPath()));
		ObjectInputStream oi = new ObjectInputStream(fi);

		// Read objects
		Filter fl = (Filter) oi.readObject();

		oi.close();
		fi.close();
		
		return fl;
		
		}
		catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		

	}
	
}




