import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


// Source http://www.baeldung.com/java-write-to-file

public class Writer { 
	public void csvWriter(String line) 
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Rachel\\Downloads\\study\\OR\\StrongestWifi.csv"));
		writer.write(line);

		writer.close();
	}
}
