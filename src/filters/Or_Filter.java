package filters;

import project.*;


/**
 * 
 * @author  source code : Boaz Ben-Moshe
 *
 */

public class Or_Filter implements Filter{
	private Filter _f1, _f2;
	
	
	
	public Or_Filter(Filter f1, Filter f2) {
		_f1 = f1;
		_f2 = f2;
	}

	public boolean test(WifiScan wifi) {
		return _f1.test(wifi) || _f2.test(wifi);
	}

	public String toString() {
		return "("+_f1+" or "+_f2+")";
	}
}