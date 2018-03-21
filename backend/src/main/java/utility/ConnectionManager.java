package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionManager {
	private String userName;
	private String password;
	private String dbUrl;

	private void init() {
		try (Scanner scanner = new Scanner(new File("creds.properties"))) {
			userName = scanner.nextLine().split("\\|")[1].trim();
			password = scanner.nextLine().split("\\|")[1].trim();
			dbUrl = scanner.nextLine().split("\\|")[1].trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		if(userName == null) {
			init();
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.exit(1);
		}
		return DriverManager.getConnection(dbUrl, userName, password);
	}

}
