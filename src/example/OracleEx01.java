package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleEx01 {
	private Connection con;
	public OracleEx01() {
		String query = "select * from quiz";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","team4","1111");
			System.out.println("connect");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OracleEx01();

	}

}
