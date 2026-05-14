package Models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test { 

    public static void main(String[] args) {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=SampleDB03ws";
        String user = "sa";
        String password = "12345";

        try {
            // Load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Connect
            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("✅ Connected successfully!");
            }

            // Test query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Registration");

            while (rs.next()) {
                System.out.println(
                        rs.getString("userName") + " - " +
                        rs.getString("password")
                );
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}