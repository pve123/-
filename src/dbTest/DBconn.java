package dbTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBconn {
   private static Properties prop;
   private static Connection con;
   
   public static Connection getConnection() {
      if(con==null) {
         String file ="src\\resources\\jdbc.properties";
         try(FileInputStream fis = new FileInputStream(file)){
            prop = new Properties();
            prop.load(fis);
            
            Class.forName(prop.getProperty("driver"));
            con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return con;
   }
   
   public static void close(Statement stmt) {
         try {
            if(stmt!=null) stmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      
   }
   public static void close(Statement stmt,ResultSet rs) {		
		try {
			if(stmt!=null)stmt.close();
			if(rs!= null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   public static void close() {
      try {
         if(con!=null) con.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}