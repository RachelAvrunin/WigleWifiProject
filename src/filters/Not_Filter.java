package filters;

import project.*;

/**
 * 
 * @author  source code : Boaz Ben-Moshe
 *
 */

public class Not_Filter implements Filter{
	
	private Filter _filter;
	
	
	public Not_Filter(Filter f) {
		_filter = f;
	}
	
	public boolean test(WifiScan wifi) {
		return !(_filter.test(wifi));
	}

	public String toString() {
		return "not("+_filter+")";
	}
}