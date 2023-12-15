package src.Library_package;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
public class setup extends JFrame {
    private JFrame f;
	private JPanel p;
	private JTextField textField;
	private JPasswordField pf1;
	private JPasswordField pf2;
    private boolean b;
    char[] password1, password2;
    
	public static void main(String[] args) {
		new setup();
	}
	public setup() {
		
		f = new JFrame();
		f.setTitle("Setup");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		p = new JPanel();
		
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel title = new JLabel("Setup ");
		title.setFont(new Font("Sitka Banner", Font.PLAIN, 21));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 414, 41);
		p.add(title);
		
		JLabel lblSubTitle = new JLabel("Enter the user details to continue");
		lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitle.setBounds(99, 58, 245, 28);
		p.add(lblSubTitle);
		
		JLabel name = new JLabel("Name");
		name.setBounds(10, 97, 70, 21);
		p.add(name);
		
		textField = new JTextField();
		textField.setBounds(99, 97, 245, 20);
		p.add(textField);
		textField.setColumns(10);
		
		JLabel pass = new JLabel("Password");
		pass.setBounds(10, 129, 70, 21);
		p.add(pass);
		
		JLabel lbl3 = new JLabel("Retype password");
		lbl3.setBounds(10, 161, 84, 21);
		p.add(lbl3);
		
		pf1 = new JPasswordField();
		pf1.setBounds(99, 128, 245, 21);
		p.add(pf1);
		
		pf2 = new JPasswordField();
		pf2.setBounds(99, 161, 245, 21);
		p.add(pf2);
		
		JButton submit = new JButton("Sign up");
		submit.setBounds(167, 214, 89, 23);
		p.add(submit);
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				char[] password1 = pf1.getPassword();
				char[] password2 = pf2.getPassword();
				
				for(int i = 0; i < ((password1.length>=password2.length)?password1.length:password2.length); i++){
			         if(password1[i] != password2[i]){
			        	 JOptionPane.showMessageDialog(null, "Please retype the password correctly", "Error", JOptionPane.ERROR_MESSAGE);
			             break;
			         }
			         else
			        	 b = true;
			     }
			}
		});
		if(b == true){
			JOptionPane.showMessageDialog(null, "Your profile has been registered", "Success", JOptionPane.PLAIN_MESSAGE);
		}
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver");   //to establish connection with the database using JDBC driver
		      Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
		      Statement st = connect.createStatement();
		String query = "CREATE DATABASE Library";
		st.executeQuery(query);
		query = "CREATE TABLE books("
				+ "Book Serial no. INT,"
				+ "Book Name TEXT,"
				+ "Author name TEXT,"
				+ "Check out date TEXT,"
				+ "Due date TEXT,"
				+ "Check in date TEXT,"
				+ "Check out condition TINYTEXT,"
				+ "Check in condition TINYTEXT,"
				+ "Name of Issuee TEXT,"
				+ "ISBN code TINYTEXT,"
				+ "Book Location TEXT,"
				+ "Book Status TINYTEXT,"
				+ "MRP INT,"
				+ "PRIMARY KEY(Book Serial no.))";
		st.executeQuery(query);
		query = "CREATE TABLE customers("
				+ "Customer ID INT,"
				+ "Fname TEXT,"
				+ "Lname TEXT,"
				+ "Date of Birth TEXT,"
				+ "Telephone BIGINT,"
				+ "Email Address TEXT,"
				+ "Occupation MEDIUMTEXT,"
				+ "Membership TEXT,"
				+ "Membership validity TEXT,"
				+ "Credit Points SMALLINT,"
				+ "Pending Penalty SMALLINT,"
				+ "Book Serial no. INT,"
				+ "PRIMARY KEY(Customer ID))";
		st.executeQuery(query);
		query = "CREATE TABLE employees("
				+ "Work ID INT,"
				+ "Employee name TEXT,"
				+ "Password INT,"
				+ "Password Hint INT)";
		st.executeQuery(query);
		JOptionPane.showMessageDialog(null, "Your database has been setup successfully. Your login id has been saved. You can now close setup", "Success", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, "Thank you for choosing LibSys"); 
}catch(Exception ex){JOptionPane.showMessageDialog(null, "Sorry! Setup could not be completed due to some error.", "Error", JOptionPane.ERROR_MESSAGE);		
	}
  }
}
