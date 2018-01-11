import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * this class is responsible for writing the file
 * Source http://www.baeldung.com/java-write-to-file
 * 
 * @author Rachel
 */
public class Writer { 

	/**
	 * this function gets a String and write a file
	 * 
	 * @param line
	 * @throws IOException
	 */
	public void csvWriter(String line , String filename) 
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(line);
		writer.close();
	}
}
