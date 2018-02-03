package SQL;

public class Connecting {

	@SuppressWarnings("unused")
	private String ip , port , url , user , pass , tableName , dbName;
	
	public Connecting(String ip , String port , String user , String pass , String tablename , String dbName){
		this.ip = ip;
		this.port = port;
		this.url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
		this.user = user;
		this.pass = pass;
		this.tableName = tablename;
		this.dbName = dbName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
