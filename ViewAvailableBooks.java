package src.Library_package;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class ViewAvailableBooks  {
    private JFrame f = new JFrame("Available books");
	private JPanel p;
	private JTable table;
    String query;
	
	public void getList() {
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver");        //Connecting to SQL database with JDBC driver
		      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		
		      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setBounds(100, 100, 450, 300);
		p = new JPanel();
		f.setContentPane(p);
		p.setLayout(null);
		f.setVisible(true);
		
		JLabel Title = new JLabel("View Available Books");
		Title.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(5, 5, 424, 41);
		p.add(Title);
		
		JTextPane display = new JTextPane();
		display.setBounds(79, 211, 279, -160);
		p.add(display);
		
		String ID = JOptionPane.showInputDialog(null, "Enter Customer ID");            //get customer id from user
	    rs = st.executeQuery("SELECT * FROM `customers` WHERE `Customer ID` = '" + ID + "'");  
	    String membership = null;
	    
	    //get available books according to membership
	    while(rs.next()){
	    	membership = rs.getString("Membership");
	    }
	    if(membership.equals("Platinum")){
	    	query = "SELECT `Book Name`, `Author name` FROM `books` WHERE `Book Status` = 'Available'";
	    }
	    else if(membership.equals("Gold")){
	    	query = "SELECT `Book Name`, `Author name` FROM `books` WHERE `MRP` <=500 && `Book Status` = 'Available'"; 
	    }
	    else if(membership.equals("Silver")){
	    	query = "SELECT `Book Name`, `Author name` FROM `books` WHERE `MRP` <=300 && `Book Status` = 'Available'" ;
	    }
	    
	    rs = st.executeQuery(query);
	    table = new JTable();
		table.setBounds(10, 69, 414, 206);
		p.add(table);
		
			table.setModel(DbUtils.resultSetToTableModel(rs));	//assigning values to the table model
	        connect.close();
	    
	}catch(Exception ex){System.out.println(ex);}
	}
public static void main(String[] args) {
		ViewAvailableBooks vw = new ViewAvailableBooks();
		vw.getList();
	}
}

