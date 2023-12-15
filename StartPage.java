package src.Library_package;
import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.proteanit.sql.DbUtils;
import java.net.*;
import java.io.*;

public class StartPage  {

	private JPanel p;
    private JFrame f;
    int hours = 0, minutes = 0, seconds = 0; String time = "";
    String date1;
    private JTable table_1;
    private JFrame f1;
	private JPanel p1;
	private JTextField textField;
	private JPasswordField passwordField;
    boolean b;
    String sysip = ""; 
    
	public void getIP() throws Exception{
		    
		    InetAddress lh = InetAddress.getLocalHost(); 
	        System.out.println("System IP Address : " + 
	                      (lh.getHostAddress()).trim()); 
	  
	        // Find public IP address 
	        
	        try
	        { 
	            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
	  
	            BufferedReader sc = 
	            new BufferedReader(new InputStreamReader(url_name.openStream())); 
	  
	            // reads system IPAddress 
	            sysip = sc.readLine().trim(); 
	        } 
	        catch (Exception e) 
	        { 
	            sysip = "Cannot Execute Properly"; 
	        } 
	        System.out.println("Public IP Address: " + sysip +"\n");
	        
	}
	
    public boolean getLogin() {             //method to connect to database and login
        test.main(null);
    	f1= new JFrame();                   // new JFrame
		f1.setTitle("LibSys User Login");
		f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f1.setBounds(100, 100, 450, 179);
		p1 = new JPanel();
		f1.setVisible(true);
		f1.setContentPane(p1);
		p1.setLayout(null);
		
		// GUI layout 
		
		JLabel Title = new JLabel("Login");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		Title.setBounds(5, 5, 424, 29);
		p1.add(Title);
		
		textField = new JTextField();
		textField.setBounds(104, 45, 284, 20);
		p1.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Login ID");
		lblUsername.setBounds(15, 45, 84, 14);
		p1.add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 76, 284, 20);
		p1.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(15, 79, 84, 14);
		p1.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(335, 107, 89, 23);
		p1.add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setBounds(10, 107, 140, 20);
		p1.add(btnForgotPassword);
			      
		  btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String loginId = textField.getText();     
				
				    
		              try{
		    		      Class.forName("com.mysql.cj.jdbc.Driver");   //to establish connection with the database using JDBC driver
		    		      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		    		      Statement st = connect.createStatement();
		    		      ResultSet rs;
		    		  String query =  "GRANT SELECT, INSERT, UPDATE, DELETE ON library.* TO user_profile@'" + sysip +"' IDENTIFIED BY 'sGvIYE5Uv4BcptDg'";   
		    		  st.executeQuery(query);
		    		  
		    		  query = "SELECT `Password` FROM `employees` WHERE `Work ID` = " + loginId;
		    		  rs = st.executeQuery(query);           //executing query in SQL
				      String val1 = "";
				      while(rs.next()){
				    	  val1 = rs.getString("Password");
				      }
				      char[] password = passwordField.getPassword();   //initializing password field
				      String val2 = "";
				      for(int i = 0; i<password.length; i++){
				    	  val2 += password[i];
				      }
				      if(val1.equals(val2)){                   //password verification 
				    	  JOptionPane.showMessageDialog(null, "Successfully logged in..", "LibSys", JOptionPane.INFORMATION_MESSAGE);
				          
				    	  b = true;
				    	  
				      }
				      else{
				    	  JOptionPane.showMessageDialog(null, "Wrong ID or password", "LibSys", JOptionPane.ERROR_MESSAGE);
				          b = false;
				      }
				      }catch(Exception ex){System.out.println(ex);}
			}
		  }
		);
		
		btnForgotPassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				try{
				      Class.forName("com.mysql.cj.jdbc.Driver");
				      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
				      Statement st = connect.createStatement();
				      ResultSet rs;
				
				      String loginId = textField.getText();
				      if(loginId.equals(null))
				    	  JOptionPane.showMessageDialog(null, "Please enter login ID", "Error", JOptionPane.ERROR_MESSAGE);
				      else {
				      String query = "SELECT * FROM `employees` WHERE `Work ID` = " + loginId;
				      rs = st.executeQuery(query);      //execute query
				      String hint = null;
				      
				      while(rs.next()){
					     hint = rs.getString("Password Hint");
				      }
				      JOptionPane.showMessageDialog(null, "The password hint is" + hint, "Password Hint", JOptionPane.INFORMATION_MESSAGE);
				      }
				}catch(Exception ex){JOptionPane.showMessageDialog(null, "Could not connect to server", "Connection Error", JOptionPane.ERROR_MESSAGE);}
				
	}
			});
		try {
			Thread.sleep(20000);       // to delay the process
			System.out.println(b);
			
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		
		return b;
		
	}
    
	public void getPage() {
	   try{
		   
		f = new JFrame();
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
		
		f.setTitle("LibSys Library Management Systems");
		
		f.setBounds(100, 100, 468, 350);
		
		JMenuBar menuBar = new JMenuBar();
		f.setJMenuBar(menuBar);
		
		JMenu mnCustomer = new JMenu("Customer");
		menuBar.add(mnCustomer);
		
		JMenuItem a1 = new JMenuItem("Add customer");
		mnCustomer.add(a1);
		a1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddCustomers.main(null);              //calling main method from AddCustomers class
			}
		});
		
		JMenuItem a2 = new JMenuItem("Edit Customer info.");
		mnCustomer.add(a2);
		a2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				EditCustomers.main(null);            //calling main method from EditCustomers class
			}
		});
		
		JMenuItem a3 = new JMenuItem("View Customer info.");
		mnCustomer.add(a3);
		a3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ViewCustomerDetails.main(null);     //calling main method from ViewCustomerDetails class
			} 
		});
		
		JMenuItem a4 = new JMenuItem("Delete Customer");
		mnCustomer.add(a4);
		a4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DeleteCustomer.main(null);         //calling main method from DeleteCustomers class
			}
		});
		
		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);
		
		
		JMenuItem b1 = new JMenuItem("Add Book");
		mnBooks.add(b1);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddBooks.main(null);              //calling main method from AddBooks class
			}
		});
		
		JMenuItem b2 = new JMenuItem("View Book Details");
		mnBooks.add(b2);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ViewBookDetails.main(null);      //calling main method from ViewBookDetails class
			}
		});
		
		JMenuItem b3 = new JMenuItem("Edit Book Details");
		mnBooks.add(b3);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				EditBookDetails.main(null);       //calling main method from EditBookDetails class
			}
		});
				
		JMenuItem b4 = new JMenuItem("Remove Book");
		mnBooks.add(b4);
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				RemoveBook.main(null);            //calling main method from RemoveBook class
			}
		});
		
		JMenu mnNewMenu = new JMenu("Inventory");
		menuBar.add(mnNewMenu);
		
		JMenuItem c1 = new JMenuItem("View Details");
		mnNewMenu.add(c1);
		c1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Inventory1.main(null);            //calling main method from Inventory1 class
			}
		});	    		
		
		JMenu QM = new JMenu("Query Management");
		menuBar.add(QM);
		
		JMenu mnCustomerQuery = new JMenu("Customer Query");
		QM.add(mnCustomerQuery);
		
		JMenuItem mem = new JMenuItem("Membership validity");
		mnCustomerQuery.add(mem);
		mem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MembershipValidity.main(null);    //calling main method from MembershipValidity class
			}
		});
		
		JMenuItem dl = new JMenuItem("Defaulter list");
		mnCustomerQuery.add(dl);
		dl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DefaulterList.main(null);         //calling main method from DefaulterList class
			}
		});
		
		JMenu d2 = new JMenu("Book Query");
		QM.add(d2);
		JMenuItem i1 = new JMenuItem("Issue Book");
		JMenuItem i2 = new JMenuItem("Return Book");
		JMenuItem i3 = new JMenuItem("View Available books");
		d2.add(i1);
		i1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					IssueBook.main(null);             //calling main method from IssueBook class
				} catch (IOException e1) {
					System.out.println(e1);
				}
			}
		});
		d2.add(i2);
		i2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ReturnBook.main(null);                //calling main method from ReturnBook class
			}
		});
		d2.add(i3);
		i3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ViewAvailableBooks.main(null);        //calling main method from ViewAvailableBooks class
			}
		});
		
		JButton btnHelp = new JButton("Help");
		menuBar.add(btnHelp);
		btnHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//opening help file  
				try{
						String path = "C:\\Backup Data\\workspace\\Comp_project\\src\\Library_package\\help.txt";
						File f = new File(path);
						Desktop.getDesktop().open(f);  //function to open file
		            }catch(Exception ex){System.out.println(ex);}
					}

		});
		p = new JPanel();
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel t = new JLabel("");
		t.setHorizontalAlignment(SwingConstants.CENTER);
		t.setBounds(355, 11, 87, 22);
		p.add(t);
		
		JLabel lblWelcomeToLibsys = new JLabel("Welcome to LibSys");
		lblWelcomeToLibsys.setFont(new Font("Cambria", Font.PLAIN, 17));
		lblWelcomeToLibsys.setBounds(10, 11, 335, 36);
		p.add(lblWelcomeToLibsys);
		
		JLabel lblNewLabel = new JLabel("Books due today :");
		lblNewLabel.setBounds(20, 58, 129, 22);
		p.add(lblNewLabel);
		
		table_1 = new JTable();
		table_1.setBounds(20, 91, 281, 134);
		p.add(table_1);
	   	     
	    	 Class.forName("com.mysql.cj.jdbc.Driver");
	    	 Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		      
		      SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
	            Calendar cal1 = Calendar.getInstance();
	            date1 = sdf.format(cal1.getTime());
	            System.out.println(date1);
		      String query = "SELECT `Book Name`, `Name of Issuee` FROM `books` WHERE `Due date` = '" + date1 + "'";
	    	    rs = st.executeQuery(query);
	    	    table_1.setModel(DbUtils.resultSetToTableModel(rs));	
	    	    
	    	    JButton btnLogout = new JButton("Logout");
	    	    btnLogout.setBounds(353, 252, 89, 23);
	    	    p.add(btnLogout);
	    	    btnLogout.addActionListener(new ActionListener(){
	    			public void actionPerformed(ActionEvent e){
	    				JOptionPane.showMessageDialog(null, "Closing....", "LibSys", JOptionPane.INFORMATION_MESSAGE);
	    				System.exit(0);
	    			}
	    		});	   
	    	    
		      
	         while (true) {  
	       // get the time of the day
	            Calendar cal = Calendar.getInstance();  
	            hours = cal.get( Calendar.HOUR_OF_DAY );  
	            if ( hours > 12 ) hours -= 12;  
	            minutes = cal.get( Calendar.MINUTE );  
	            seconds = cal.get( Calendar.SECOND );  
	  
	            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");  
	            Date date = cal.getTime();  
	            time = format.format(date);  
	  
	            t.setText(time);
	            
	            }
	        
	    } 
		
	      catch (Exception e) { System.out.println(e);
	         
	      }
	    
	}
	public static void main(String[] args) {
		StartPage sp = new StartPage();
		boolean a = sp.getLogin();
		if(a == true)
			sp.getPage();
	}

}
