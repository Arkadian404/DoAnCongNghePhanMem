package shop.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private final String serverName = "DESKTOP-V2MH4TM";
	private final String dbName = "Shop2H2T";
	private final String portNumber = "1433";
	private final String instance = ""; 
	private final String userID = "sa";
	private final String password = "duc2112002"; 
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance
				+ ";integratedSecurity=true;databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";integratedSecurity=true;databaseName="
					+ dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
	
	public static void main(String[] args) throws Exception {
		DbConnection conn = new DbConnection();
		System.out.println(conn.getConnection());
	}
}
