package src.Library_package;

import java.awt.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class DefaulterList {
    private JFrame f;
	private JPanel p;
	private JTable table;
 
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	Date d1 = new Date();
	Date d2 = new Date();
    Calendar c1 = new GregorianCalendar();
    Calendar c2 = new GregorianCalendar();
    private JLabel lblNameOfDefaulters;
    private JLabel lblBookIssued;
		
	@SuppressWarnings("resource")   //to suppress resource leak on line 78
	public void view() {
		//GUI and contents
		f = new JFrame();
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		p = new JPanel();
		f.setVisible(true);
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel lblListOfDefaulters = new JLabel("List of Defaulters");
		lblListOfDefaulters.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		lblListOfDefaulters.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfDefaulters.setBounds(10, 11, 414, 43);
		p.add(lblListOfDefaulters);
		
		table = new JTable();
		table.setBounds(22, 80, 385, 170);
		p.add(table);
		
		lblNameOfDefaulters = new JLabel("Names ");
		lblNameOfDefaulters.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameOfDefaulters.setBounds(20, 55, 185, 19);
		p.add(lblNameOfDefaulters);
		
		lblBookIssued = new JLabel("Book issued");
		lblBookIssued.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookIssued.setBounds(215, 55, 192, 19);
		p.add(lblBookIssued);
		
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver");                //connection with SQL database through JDBC driver
		      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		      
		      rs = st.executeQuery("SELECT * FROM `books`");
		      
		      while(rs.next()){
		    	  d1 = sdf.parse(rs.getString("Check out date"));   // get date in specified format
	          	  c1.setTime(d1);
	          	  c1.add(Calendar.DATE, 21);             //get date 21 days ahead of check out date
		    	  d2 = c1.getTime();                     //get added time
		          rs = st.executeQuery("SELECT `Name of Issuee`,`Book Name` FROM `books` WHERE `Check in date` != '" + d2 + "' && `Book Status` = 'Issued'");
		          table.setModel(DbUtils.resultSetToTableModel(rs));	//assigning data to table
		          
		      }
		      rs.close();
		}catch(Exception ex){System.out.println(ex);}
	}
	public static void main(String[] args) {  //main method
		DefaulterList obj1 = new DefaulterList();
		obj1.view();
	}
}
