package src.Library_package;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.sql.*;

public class Inventory1  {

	private JPanel p;
	private JTable table;
    private JFrame f; 
	
	public void view() {
		
		f = new JFrame();
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setTitle("LibSys");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 1184, 469);
		
		p = new JPanel();
		f.setVisible(true);
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory");
		lblNewLabel.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(5, 5, 1153, 35);
		p.add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(10, 51, 1148, 368);
		p.add(table);
		
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver");         //Connecting to SQL database through JDBC driver
		      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		      Object[] options = {"YES", "NO"};
		      //choice to sort inventory data or display without sorting 
		      int choice = JOptionPane.showOptionDialog(null, 
		    		 "Do you want to sort the data by book name?",
		             "LibSys",
		             JOptionPane.YES_NO_CANCEL_OPTION,
		             JOptionPane.QUESTION_MESSAGE,
		             null,
		             options,
		             options[1]); 
		     if(choice == JOptionPane.YES_OPTION){
		    	  rs = st.executeQuery("SELECT `Book Name`, COUNT(`Book Name`) FROM `books` GROUP BY `Book Name`");
		          table.setModel(DbUtils.resultSetToTableModel(rs));   //assigning data to table
		     }
		          else{
		    	  rs = st.executeQuery("SELECT `Book Serial no.`, `Book Name`, `Author name`, `Book Location`, `Book Status` FROM `books`");
			      table.setModel(DbUtils.resultSetToTableModel(rs));   //assigning data to table
		          }
		      }catch(Exception ex){System.out.println(ex);}
	}
		public static void main(String[] args) {
			Inventory1 obj = new Inventory1();
			obj.view();
		}
}
