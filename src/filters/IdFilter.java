package filters;

import project.WifiScan;

public class IdFilter implements Filter {
	private String id;

	/**
	 * this function implements the filter class 
	 * and filter by id
	 * 
	 * @param id
	 */
	public IdFilter(String id) {
		this.id = id;
	}

	@Override
	public boolean test(WifiScan item) {
		return item.id.equals(id);
	}
}