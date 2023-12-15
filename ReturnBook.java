package src.Library_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class ReturnBook {
 
	JFrame f = new JFrame();
	private JPanel p;
	private JTextField bname;
	private JTextField BSN;
	private JTextField CustID;
	private JTextField Ret_date;
	Calendar c1 = new GregorianCalendar();
	Calendar c2 = new GregorianCalendar();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
    public int daysBetween(Date d1, Date d2){                //method to calculate days between two dates
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}
    public void update() {
		
    	//GUI and its components
    	f.setTitle("ReturnBook");
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 250);
		p = new JPanel();
		
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel title = new JLabel("Return a Book");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		title.setBounds(105, 5, 199, 35);
		p.add(title);
		
		JLabel l1 = new JLabel("Name of the Book");
	    l1.setBounds(10, 60, 106, 14);
		p.add(l1);
		
		JLabel l2 = new JLabel("Book Serial no");
		l2.setBounds(10, 85, 106, 14);
		p.add(l2);
		
		JLabel l3 = new JLabel("Customer ID");
		l3.setBounds(10, 110, 106, 14);
		p.add(l3);
		
		bname = new JTextField();
		bname.setBounds(126, 55, 280, 20);
		p.add(bname);
		bname.setColumns(10);
		
		BSN = new JTextField();
		BSN.setBounds(126, 82, 96, 20);
		p.add(BSN);
		BSN.setColumns(10);
		
		CustID = new JTextField();
		CustID.setBounds(126, 107, 280, 20);
		p.add(CustID);
		CustID.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(335, 177, 89, 23);
		p.add(submit);
		
		JLabel cin = new JLabel("Date Returned");
		cin.setBounds(10, 135, 106, 14);
		p.add(cin);
		
		Ret_date = new JTextField("(dd.MM.yyyy)");
		Ret_date.setBounds(126, 132, 86, 20);
		p.add(Ret_date);
		Ret_date.setColumns(10);
		
		submit.addActionListener(new ActionListener(){
			
			@SuppressWarnings("resource")    //to suppress resource leak warning on line 114
			public void actionPerformed(ActionEvent e) {
				
				//extracting text from textfields
				String bsn = BSN.getText();
				String CID = CustID.getText();
				String cinDate = Ret_date.getText();
				try{
					 Class.forName("com.mysql.cj.jdbc.Driver");   //connecting to SQL database with JDBC driver
					 Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
				      Statement st = connect.createStatement();
				      ResultSet rs;
				      
				      String coutDate = null;
				      String q1 = "SELECT * FROM `books` WHERE `Book Serial no.` = '" + bsn+ "'";
				      rs = st.executeQuery(q1);               //execute select query
				      while(rs.next())
				          coutDate = rs.getString("Check out date");
				      
				      Date d1 = sdf.parse(coutDate);
				      Date d2 = sdf.parse(cinDate);
				      c1.setTime(d1);
				      c2.setTime(d2);
				      int days = daysBetween(c1.getTime(), c2.getTime());
				      if(days <= 21){
				    	  rs = st.executeQuery("SELECT * FROM `customers` WHERE `Customer ID` = '" + CID + "'");
				    	  if(rs.next()){
				    		  int pts = Integer.parseInt(rs.getString("Credit Points"));
					    	  pts = pts + 2;              //calculate credit points
					    	  st.executeUpdate("UPDATE `customers` SET `Book Serial no.` = 0,`Credit Points` = '" + pts + "' WHERE `Customer ID` = '" + CID + "'");
				    	  }
				      }
				      else 
				    	  rs = st.executeQuery("SELECT * FROM `customers` WHERE `Customer ID` = '" + CID + "'");
			    	  if(rs.next()){
			    		  int pp = Integer.parseInt(rs.getString("Pending Penalty"));
				    	  pp = pp + 2 * (days - 21);              // calculate pending penalty
				    	  st.executeUpdate("UPDATE `customers` SET `Book Serial no` = 0,`Pending Penalty` = '" + pp + "' WHERE `Customer ID` = '" + CID + "'");
			    	  }
				    	  
				    	  
				      String q3 = "UPDATE `books` SET `Check in date` = '" + cinDate + "', `Book Status` = 'Available' WHERE `Book Serial no.` = '"+ bsn + "'";
				      st.executeUpdate(q3);           //execute update query
				      JOptionPane.showMessageDialog(null, "Your request has been completed");
				      
				      System.exit(0);				      
				      
				}catch(Exception ex){System.out.println(ex);}
			}
		});
		f.setVisible(true);
    }
public static void main(String[] args) {
		ReturnBook t1 = new ReturnBook();
		t1.update();
	}
}

