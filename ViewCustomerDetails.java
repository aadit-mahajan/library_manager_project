package src.Library_package;
import javax.swing.*;
import java.awt.Font;
import java.sql.*;

public class ViewCustomerDetails {
	
	    private JFrame f;
		private JPanel p;

		public void view() {
			try{
			      Class.forName("com.mysql.cj.jdbc.Driver");
			      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");   //connecting with the SQL database though JDBC driver
			      Statement st = connect.createStatement();
			      ResultSet rs;
			      
			      String ID = JOptionPane.showInputDialog("Enter Customer ID");
			      String query = "SELECT * FROM `customers` WHERE `Customer ID` = " + ID;
			      rs = st.executeQuery(query);
			      while(rs.next()){
			    	
			//GUI and its components    	  
			f = new JFrame();
			f.setVisible(true);
			f.setTitle("LibSys");
			f.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setBounds(100, 100, 450, 250);
			p = new JPanel();
			
			f.setContentPane(p);
			p.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("View Customer Details");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
			lblNewLabel.setBounds(10, 5, 419, 40);
			p.add(lblNewLabel);
			
			JLabel lblCustomerFirstName = new JLabel("Customer Name");
			lblCustomerFirstName.setBounds(20, 56, 112, 14);
			p.add(lblCustomerFirstName);
			
			JLabel lblNewLabel_1 = new JLabel("Customer ID");
			lblNewLabel_1.setBounds(20, 81, 112, 14);
			p.add(lblNewLabel_1);
			
			JLabel lblMembership = new JLabel("Membership");
			lblMembership.setBounds(20, 106, 112, 14);
			p.add(lblMembership);
			
			JLabel lblDateOfBirth = new JLabel("Date of Birth");
			lblDateOfBirth.setBounds(20, 131, 112, 14);
			p.add(lblDateOfBirth);
			
			JLabel lblTelephone = new JLabel("Telephone");
			lblTelephone.setBounds(20, 156, 112, 14);
			p.add(lblTelephone);
			
			JLabel lblEmailAddress = new JLabel("Email Address");
			lblEmailAddress.setBounds(20, 181, 112, 14);
			p.add(lblEmailAddress);
			
			//assigning values from result set to labels
			JLabel custname = new JLabel(rs.getString("Fname") + " " + rs.getString("Lname"));
			custname.setBounds(142, 56, 282, 14);
			p.add(custname);
			
			JLabel id = new JLabel(rs.getString("Customer ID"));
			id.setBounds(142, 81, 282, 14);
			p.add(id);
			
			JLabel mem = new JLabel(rs.getString("Membership"));
			mem.setBounds(142, 106, 282, 14);
			p.add(mem);
			
			JLabel dob = new JLabel(rs.getString("Date of Birth"));
			dob.setBounds(142, 131, 282, 14);
			p.add(dob);
			
			JLabel phone = new JLabel(rs.getString("Telephone"));
			phone.setBounds(142, 156, 282, 14);
			p.add(phone);
			
			JLabel email = new JLabel(rs.getString("Email Address"));
			email.setBounds(142, 181, 282, 14);
			p.add(email);
			      }
		}catch(Exception ex){System.out.println(ex);}
	  }
		public static void main(String[] args) {
			ViewCustomerDetails obj = new ViewCustomerDetails();
			obj.view();
		}
	}
