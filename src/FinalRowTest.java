import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @author Rachel
 * 
 *class to check the FinalRow class
 */
public class FinalRowTest {
	/**
	 * check if the char '[' and ']' are removed 
	 * and the toString function output is good
	 */
	@Test
	public void test() {

		FinalRow r = new FinalRow("12/7/17","19:50:50","samsuung",32.56,33.4,17.3);
		r.addline(-30, "sdvsdf", "adfdsv", 3);
		r.addline(-20, "sdvsdd", "adsfdsv", 1);
		String str=r.toString();
		assertEquals("12/7/17,19:50:50,samsuung,32.56,33.4,17.3,-30,adfdsv,sdvsdf,3, -20,adsfdsv,sdvsdd,1" + System.lineSeparator(),str);

		FinalRow x = new FinalRow("12/7/17","19:50:50","samsuung",32.56,33.4,17.3);
		str=x.toString();
		assertEquals("12/7/17,19:50:50,samsuung,32.56,33.4,17.3," + System.lineSeparator(),str);

	}

}
