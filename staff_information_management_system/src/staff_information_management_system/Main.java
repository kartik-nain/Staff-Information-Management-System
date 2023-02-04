package staff_information_management_system;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class Main {       

	public static void main(String[] args) throws IOException  //main method with exception handling
	, ClassNotFoundException, SQLException
		{
		Information pro = new Information();
		pro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pro.setSize(700, 440);
		pro.setVisible(true);
		}
}
