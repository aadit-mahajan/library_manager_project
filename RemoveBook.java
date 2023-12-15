package src.Library_package;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;

public class RemoveBook {

	public void delete(){
	
	String BSN = JOptionPane.showInputDialog("Enter Book Serial no. of the book you want to delete");
	try{
	      Class.forName("com.mysql.cj.jdbc.Driver");   //Connecting to SQL database with JDBC driver
	      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
	      Statement st = connect.createStatement();
	      
	      if(JOptionPane.showConfirmDialog(null, "Do you really want to delete this book?") == JOptionPane.YES_OPTION){
	          
	    	  String query= "DELETE FROM `books` WHERE `Book Serial no.` = " + BSN;
	          st.executeUpdate(query);  //execute delete query
	          JOptionPane.showMessageDialog(null, "Book deleted from inventory", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	      }
	          System.exit(0);
	}catch(Exception ex){System.out.println(ex);}
}
public static void main(String[] args) {
		// TODO Auto-generated method stub
RemoveBook r1 = new RemoveBook();
r1.delete();
	}

}
