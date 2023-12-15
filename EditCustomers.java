package src.Library_package;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class EditCustomers {

	JFrame f = new JFrame();
	private JPanel p;
	private JTextField fname;
	private JTextField phone;
	private JTextField email;
	private JTextField dob;
	private JTextField occpn;
	private JLabel membership1;
    String insert_query;
 
    private JTextField lname;
	private ButtonGroup bg = new ButtonGroup();
	String membership = null;
	
	public void editMembers() {
		
		//GUI and its components
		f.setTitle("LibSys");
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 350);
		p = new JPanel();
		f.setVisible(true);
		f.setContentPane(p);
		p.setLayout(null); 
		
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver");    //connecting to SQL database through JDBC driver
		      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		      
		      String query = null;
		      String ID = JOptionPane.showInputDialog(null, "Enter Customer ID");
		      rs = st.executeQuery("SELECT * FROM `customers`");
		      while(rs.next()){
		    	  
		    	  //checking if customer ID exists
		    	  if(rs.getString("Customer ID").equals(ID)){ 
		    		  query = "SELECT * FROM `customers` WHERE `Customer ID` =  " + ID ;
		    	      break;
		    	  }
		    	  else
		    		  continue;
		      }
		      
		      if(query == null){      //display error message
		    	  JOptionPane.showMessageDialog(null, "Sorry! Could not find profile \nTry Again!", "Error", JOptionPane.ERROR_MESSAGE);
		    	  System.exit(0);
		      }
		      else
		    	  rs = st.executeQuery(query);
		      
		      while(rs.next()){
		        fname = new JTextField(rs.getString("Fname"));
				fname.setBounds(117, 49, 250, 20);
				p.add(fname);
				fname.setColumns(10);
				
				phone = new JTextField(rs.getString("Telephone"));
				phone.setColumns(10);
				phone.setBounds(117, 99, 250, 20);
				p.add(phone);
				
				lname = new JTextField(rs.getString("Lname"));
				lname.setColumns(10);
				lname.setBounds(117, 74, 250, 20);
				p.add(lname);
				
				email = new JTextField(rs.getString("Email Address"));
				email.setColumns(10);
				email.setBounds(117, 124, 250, 20);
				p.add(email);
				
				dob = new JTextField(rs.getString("Date of Birth"));
				dob.setColumns(10);
				dob.setBounds(117, 149, 250, 20);
				p.add(dob);
				
				occpn = new JTextField(rs.getString("Occupation"));
				occpn.setColumns(10);
				occpn.setBounds(117, 174, 250, 20);
				p.add(occpn);
				
				membership1 = new JLabel(rs.getString("Membership"));
				membership1.setBounds(155, 199, 250, 20);
				p.add(membership1);
		      }
		
		
		JLabel lblNewLabel = new JLabel("Enter Details You Wish to Change");
		lblNewLabel.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
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
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(15, 77, 77, 14);
		p.add(lblLastName);
		
		JLabel mem = new JLabel("Current Membership :-");
		mem.setBounds(15, 202, 140, 14);
		p.add(mem);
				
		JLabel lblNewLabel_1 = new JLabel("Choose one Membership : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 220, 419, 20);
		p.add(lblNewLabel_1);
		
		JRadioButton rdbtnPlatinum = new JRadioButton("Platinum ");
		rdbtnPlatinum.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPlatinum.setBounds(15, 246, 115, 23);
		p.add(rdbtnPlatinum);
		
		JRadioButton rdbtnGold = new JRadioButton("Gold");
		rdbtnGold.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnGold.setBounds(132, 246, 165, 23);
		p.add(rdbtnGold);
		
		JRadioButton rdbtnSilver = new JRadioButton("Silver");
		rdbtnSilver.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSilver.setBounds(315, 246, 109, 23);
		p.add(rdbtnSilver);
		
		bg.add(rdbtnSilver);
		bg.add(rdbtnGold);
		bg.add(rdbtnPlatinum);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(335, 277, 89, 23);
		p.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{	
					final String query = "SELECT * FROM `customers` WHERE `Customer ID` =  " + ID ;
					final ResultSet rs1;
					rs1 = st.executeQuery(query);
					while(rs1.next()){
						String membership1 = rs1.getString("Membership"); 
						String membership = "none";
					
				    while(true){
				    	//checking which radio button is selected
				          if(rdbtnPlatinum.isSelected()){
							  membership = "Platinum";
				              break;
				          }
						  else if(rdbtnGold.isSelected()){
							  membership = "Gold";
							  break;
						  }
						  else if(rdbtnSilver.isSelected()){
							  membership = "Silver";
							  break;
						  }
						  else 
							  JOptionPane.showMessageDialog(null, "Please select the type of membership!", "ERROR", JOptionPane.ERROR_MESSAGE);
				    }
					 if(membership1.equals(membership) == false){    //checking if membership type has been changed 
							  if(JOptionPane.showConfirmDialog(null, "You have changed your membership. Do you wish to change membership type?") == JOptionPane.YES_OPTION)
								  if(membership.equals("Platinum"))
									  JOptionPane.showMessageDialog(null, "Please pay Rs.1000 to change to Platinum membership", "Membership change", JOptionPane.INFORMATION_MESSAGE);
								  else if(membership.equals("Gold"))
									  JOptionPane.showMessageDialog(null, "Please pay Rs.800 to change to Gold membership", "Membership change", JOptionPane.INFORMATION_MESSAGE);
								  else 
									  JOptionPane.showMessageDialog(null, "Please pay Rs.600 to change to Silver membership", "Membership upgrade", JOptionPane.INFORMATION_MESSAGE);
						  }
						  
				          }
					
				          //extracting text from text fields
						  String Fname = fname.getText();
						  
						  String Lname = lname.getText();
						  String Phone = phone.getText();
						  String Email = email.getText();
						  String DOB = dob.getText();
						  String Occupation = occpn.getText();
						  
				    String update_query = "UPDATE `customers` SET `Fname`='" + Fname + "',`Lname`='" + Lname + "',`Date of Birth`='" + DOB + "',`Telephone`='"+Phone + "',`Email Address`='" + Email + "',`Occupation`='" + Occupation + "',`Membership`='" + membership + "' WHERE `Customer ID` = '" + ID +"'";
				    st.executeUpdate(update_query);       //update the customer data
				    JOptionPane.showMessageDialog(null, "Update successful");
				    
					}		
			 catch(Exception ex2){System.out.println(ex2);}
				}
		});
		
	}catch(Exception ex){System.out.println(ex);}
		
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
EditCustomers e1 = new EditCustomers();
e1.editMembers();
	}

}
