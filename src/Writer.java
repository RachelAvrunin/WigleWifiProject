import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Rachel
 * 
 * this class is responsible for writing the file
 * Source http://www.baeldung.com/java-write-to-file
 */
public class Writer { 

	/**
	 * @param line
	 * @throws IOException
	 * 
	 * this function gets a String and write a file
	 */
	public void csvWriter(String line) 
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv"));
		writer.write(line);

		writer.close();
	}
}
