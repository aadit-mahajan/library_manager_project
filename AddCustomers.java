package src.Library_package;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddCustomers {

	JFrame f = new JFrame();
	private JPanel p;
	private JTextField fname;
	private JTextField phone;
	private JTextField email;
	private JTextField dob;
	private JTextField occpn;
    String insert_query;
    private JTextField mname;
    private JTextField lname;
	private ButtonGroup bg = new ButtonGroup();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	public void getMembers() {
		//get JFrame
		f.setTitle("LibSys");
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 350);
		p = new JPanel();
		f.setVisible(true);
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register for Membership ");
		lblNewLabel.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(5, 5, 424, 36);
		p.add(lblNewLabel);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setBounds(15, 52, 77, 14);
		p.add(lblFname);
		
		JLabel lblPhNo = new JLabel("Ph. no");
		lblPhNo.setBounds(15, 102, 72, 14);
		p.add(lblPhNo);
		
		JLabel lblEmailAddress = new JLabel("Email address");
		lblEmailAddress.setBounds(15, 127, 92, 14);
		p.add(lblEmailAddress);
		
		JLabel lblDOB = new JLabel("Date of Birth");
		lblDOB.setBounds(15, 152, 92, 14);
		p.add(lblDOB);
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(15, 177, 92, 14);
		p.add(lblOccupation);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(229, 52, 83, 14);
		p.add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(15, 77, 77, 14);
		p.add(lblLastName);
		
		fname = new JTextField();
		fname.setBounds(117, 49, 102, 20);
		p.add(fname);
		fname.setColumns(10);
		
		mname = new JTextField();
		mname.setColumns(10);
		mname.setBounds(322, 49, 102, 20);
		p.add(mname);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(117, 99, 250, 20);
		p.add(phone);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(117, 74, 148, 20);
		p.add(lname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(117, 124, 250, 20);
		p.add(email);
		
		dob = new JTextField("dd.MM.yyyy");
		dob.setColumns(10);
		dob.setBounds(117, 149, 250, 20);
		p.add(dob);
		
		occpn = new JTextField();
		occpn.setColumns(10);
		occpn.setBounds(117, 174, 250, 20);
		p.add(occpn);
		
		JLabel lblNewLabel_1 = new JLabel("Choose one Membership");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 209, 419, 20);
		p.add(lblNewLabel_1);
		
		JRadioButton rdbtnPlatinum = new JRadioButton("Platinum");
		rdbtnPlatinum.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPlatinum.setBounds(15, 236, 115, 23);
		p.add(rdbtnPlatinum);
		
		JRadioButton rdbtnGold = new JRadioButton("Gold");
		rdbtnGold.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnGold.setBounds(132, 236, 165, 23);
		p.add(rdbtnGold);
		
		JRadioButton rdbtnSilver = new JRadioButton("Silver");
		rdbtnSilver.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSilver.setBounds(315, 236, 109, 23);
		p.add(rdbtnSilver);
		
		//adding radio buttons to button group
		bg.add(rdbtnSilver);
		bg.add(rdbtnGold);
		bg.add(rdbtnPlatinum);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(335, 277, 89, 23);
		p.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			  if(JOptionPane.showConfirmDialog(null, "Are you sure you want to become a member")== JOptionPane.YES_OPTION){
				  //extracting text from textfields
				  String Fname = fname.getText();
				  Fname = Fname + " " +mname.getText();
				  String Lname = lname.getText();
				  String Phone = phone.getText();
				  String Email = email.getText();
				  String DOB = dob.getText();
				  String Occupation = occpn.getText();
		          String membership;
				  
		          while(true){ //Selecting amount of membership fee
		          if(rdbtnPlatinum.isSelected()){
					  membership = "Platinum";
		              JOptionPane.showMessageDialog(null, "Pay a membership fee of Rs.1000");
					  break;
		          }
				  else if(rdbtnGold.isSelected()){
					  membership = "Gold";
					  JOptionPane.showMessageDialog(null, "Pay a membership fee of Rs.750");
					  break;
				  }
				  else if(rdbtnSilver.isSelected()){
					  JOptionPane.showMessageDialog(null, "Pay a membership fee of Rs.500");
					  membership = "Silver";
					  break;
				  }
				  else 
					  JOptionPane.showMessageDialog(null, "Please select the type of membership!", "ERROR", JOptionPane.ERROR_MESSAGE);
		          }
		          Calendar c1 = Calendar.getInstance();
		          c1.add(Calendar.YEAR, 1);
		          String mv = c1.getTime().toString();
		          		          
		          try{
				      Class.forName("com.mysql.cj.jdbc.Driver");   //establishing connection with the JDBC driver to SQL database
				      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
				      Statement st = connect.createStatement();
		              ResultSet rs;
				      
				      rs = st.executeQuery("SELECT * FROM `customers` ORDER BY `Customer ID` DESC LIMIT 1");
				      String id = null;
				      if(rs.next())
				          id = rs.getString("Customer ID");
				      int i = Integer.parseInt(id);
				      i++;
				      
				      insert_query = "INSERT INTO `customers`(`Customer ID`,`Fname`, `Lname`, `Date of Birth`, `Telephone`, `Email Address`, `Occupation`, `Membership`,`Membership validity`, `Credit Points`, `Pending Penalty`, `Book Serial no.`) VALUES (" + i + ", '" + Fname + "','"+ Lname + "','"+DOB + "', " + Phone + ",'"+Email + "','"+Occupation + "','"+membership+"','"+ mv + "',0,0,0)";
			          st.executeUpdate(insert_query);    //adding new customer data to database
			          JOptionPane.showMessageDialog(null, "Your request is being processed", "Processing...", JOptionPane.PLAIN_MESSAGE);
			          System.exit(0);
			          
			       }catch(Exception ex){System.out.println(ex);}
			  }
			  }
		});
		
	}

   public static void main(String[] args) {
	AddCustomers obj1 = new AddCustomers();
	obj1.getMembers();
   }
}