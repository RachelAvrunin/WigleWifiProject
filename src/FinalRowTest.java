import static org.junit.Assert.*;
import org.junit.Test;

/**
 * a class to check the FinalRow class
 *
 * @author Rachel
 */
public class FinalRowTest {

	/**
	 * check if the char '[' and ']' are removed 
	 * and the toString function output is good
	 */
	@Test
	public void test1() {

		FinalRow r = new FinalRow("12/7/17","19:50:50","samsung",32.56,33.4,17.3);
		r.addline(-30, "sdvsdf", "adfdsv", 3);
		r.addline(-20, "sdvsdd", "adsfdsv", 1);
		String str=r.toString();
		assertEquals("test1 fail","12/7/17 19:50:50,samsung,32.56,33.4,17.3,2,adfdsv,sdvsdf,3,-30,adsfdsv,sdvsdd,1,-20" + System.lineSeparator(),str);

	}

	/**
	 * check if the char ',' are removed with an empty wifi
	 * and the toString function output is good
	 */
	@Test
	public void test2() {

		FinalRow x = new FinalRow("12/7/17","19:50:50","samsung",32.56,33.4,17.3);
		String str=x.toString();
		assertEquals("test2 fail","12/7/17 19:50:50,samsung,32.56,33.4,17.3,0" + System.lineSeparator(),str);

	}
}
