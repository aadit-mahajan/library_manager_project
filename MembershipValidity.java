package src.Library_package;

import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;


public class MembershipValidity {

	private JPanel p;
	private JTable table;
    private JFrame f;
	
	public void getDetails() {
		//GUI and its components
		f = new JFrame();
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setTitle("LibSys");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 301);
		p = new JPanel();
		f.setVisible(true);
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel title = new JLabel("Membership Validity");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sitka Banner", Font.PLAIN, 22));
		title.setBounds(5, 5, 424, 45);
		p.add(title);
		
		table = new JTable();
		table.setBounds(10, 85, 414, 165);
		p.add(table);
		
		JLabel lblCustomerName = new JLabel("Customer name");
		lblCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerName.setBounds(15, 55, 198, 19);
		p.add(lblCustomerName);
		
		JLabel lblMembershipValidTill = new JLabel("End Date");
		lblMembershipValidTill.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembershipValidTill.setBounds(223, 55, 101, 19);
		p.add(lblMembershipValidTill);
		
		JLabel lblContactNo = new JLabel("Contact no.");
		lblContactNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactNo.setBounds(328, 55, 101, 19);
		p.add(lblContactNo);
	    
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver");  //connecting to SQL database with JDBC driver
		      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		      
		      rs = st.executeQuery("SELECT `Fname`, `Lname`, `Membership validity`, `Telephone` FROM `customers`");
		      table.setModel(DbUtils.resultSetToTableModel(rs));  // assigning data to table model
		      
	}catch(Exception ex){System.out.println(ex);}
	}
public static void main(String[] args) {
	MembershipValidity mv = new MembershipValidity();
	mv.getDetails();
	}
}