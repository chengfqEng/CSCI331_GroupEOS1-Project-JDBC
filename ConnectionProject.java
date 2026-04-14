import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class ConnectionProject {
		public static void main(String[] args) {
			//Create a variable for connection string
			String connectionURL = "jdbc:sqlserver://localhost:8088;databaseName=AdventureWorks2022;user=sa;password=Cheeselike2@;encrypt=true;trustServerCertificate=true";
			try(Connection con = DriverManager.getConnection(connectionURL); Statement stmt = con.createStatement();){
				String SQL = "SELECT TOP 10 * FROM Person.Person";
				ResultSet rs = stmt.executeQuery(SQL);
				//Iterate through the data
				while(rs.next()) {
					System.out.println(rs.getString("FirstName")+ " "+ rs.getString("LastName"));
				}
			}
			//Handle any errors
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
}
