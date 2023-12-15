package src.Library_package;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;


public class ViewBookList {
	
	private JFrame f;
	private JPanel p;
	private JTable table_1;

public void view(){
	try{
	      Class.forName("com.mysql.cj.jdbc.Driver");// connecting with SQL database through JDBC driver
	      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
	      Statement st = connect.createStatement();
	      ResultSet rs;
	       
	        //GUI and its components
	        f = new JFrame();
	        f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setBounds(100, 100, 450, 300);
			p = new JPanel();
			f.setVisible(true);
			f.setSize(700, 600);
			f.setContentPane(p);
			p.setLayout(null);
			
			JLabel title = new JLabel("Inventory");
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
			title.setBounds(5, 5, 424, 38);
			p.add(title);
			
			table_1 = new JTable();
			table_1.setBounds(15, 54, 409, 179);
			p.add(table_1);
			
			
	      rs = st.executeQuery("SELECT * FROM `books`");    //execute select statement
	      table_1.setModel(DbUtils.resultSetToTableModel(rs));	//assigning values to table model
	}catch(Exception ex){System.out.println(ex);}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
           ViewBookList obj = new ViewBookList();
           obj.view();

	}
}
