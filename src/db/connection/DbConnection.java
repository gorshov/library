package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

   private static final String URL = "jdbc:mysql://localhost:3306/library";
   private static String DB_NAME = "library";
   private static final String USERNAME = "root";
   private static final String PASSWORDS = "root";

   public static Connection getConnectionDb() {
      try {
         return  DriverManager.getConnection(URL, USERNAME, PASSWORDS);
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }
   public static void setDbName(String dbName) {
      DB_NAME = dbName;

   }
}
