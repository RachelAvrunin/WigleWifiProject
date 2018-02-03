package GUI;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Open {


	JFileChooser chooser = new JFileChooser();

	public File PickFolder(){

		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);  //  Disable the "All files" option.

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			System.out.println("getCurrentDirectory(): " 
					+  chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " 
					+  chooser.getSelectedFile());
			return chooser.getSelectedFile();
		}
		else 	
			return chooser.getSelectedFile();
		

	}

	
	
	public File PickCSVFile(){

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		return null;


	}

	public File PickFilterFile(){

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		return null;
	}
}
