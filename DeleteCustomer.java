package src.Library_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;

public class DeleteCustomer {
	String delete_query;

public void delete(){
	try{
	      Class.forName("com.mysql.cj.jdbc.Driver");   //Connecting to SQL database through JDBC driver
	      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
	      Statement st = connect.createStatement();
	      
	      String ID = JOptionPane.showInputDialog("Enter Customer ID");
	      //confirmation for deleting profile
	      if(JOptionPane.showConfirmDialog(null, "Do you wish to delete this profile from our database?") == JOptionPane.YES_OPTION)
	      delete_query = "DELETE FROM `customers` WHERE `Customer ID` = " + ID;    //delete query 
	      
	      st.executeUpdate(delete_query);       //executing delete query
	}catch(Exception ex){System.out.println(ex);}
}  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
DeleteCustomer dc = new DeleteCustomer();
dc.delete();
	}

}
