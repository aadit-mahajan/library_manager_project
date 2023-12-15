package src.Library_package;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IssueBook {
	  JFrame f = new JFrame();
		JPanel p = new JPanel();
		JTextField t1;
		JTextField t2;
		JTextField t3;
		JTextField t4;
		JTextField t5;
		JTextField t6;
		String issue_query;

	public void issueForm() {
		
		//GUI and its components 
		
		f.setTitle("Issue a new book");
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		p = new JPanel();
		
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel l1 = new JLabel("Name of the Book");
		l1.setBounds(10, 50, 113, 14);
		p.add(l1);
		
		JLabel l2 = new JLabel("Book Serial no.");
		l2.setBounds(10, 75, 113, 14);
		p.add(l2);
		
		JLabel l3 = new JLabel("Check out condition");
		l3.setBounds(10, 100, 113, 14);
		p.add(l3);
		
		JLabel l4 = new JLabel("Customer name");
		l4.setBounds(10, 125, 113, 14);
		p.add(l4);
		
		JLabel CustID = new JLabel("Customer ID");
		CustID.setBounds(10, 151, 113, 14);
		p.add(CustID);
				
		JLabel title = new JLabel("Issue a book");
		title.setFont(new Font("Calibri", Font.PLAIN, 24));
		title.setBounds(113, 11, 189, 25);
		p.add(title);
		
		t1 = new JTextField();
		t1.setBounds(133, 47, 280, 20);
		p.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(133, 72, 117, 20);
		p.add(t2);
		t2.setColumns(10);
		
		t3 = new JTextField();
		t3.setBounds(134, 97, 116, 20);
		p.add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		t4.setBounds(133, 122, 280, 20);
		p.add(t4);
		t4.setColumns(10);
	
		t5 = new JTextField();
		t5.setBounds(133, 148, 85, 20);
	    p.add(t5);
		t5.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(300, 200, 89, 23);
		p.add(submit);
		
		JLabel date_out = new JLabel("Check out Date");
		date_out.setBounds(10, 177, 113, 14);
		p.add(date_out);
		
		t6 = new JTextField();
		t6.setBounds(133, 174, 86, 20);
		p.add(t6);
		t6.setColumns(10);
				
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//extracting text from text fields
				String text1 = t3.getText();
				String text2 = t4.getText();
				String text3 = t6.getText();
				String text4 = t2.getText();
				String text5 = t5.getText();
				try{
				      Class.forName("com.mysql.cj.jdbc.Driver");
				      Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
				      Statement st = connect.createStatement();
				      ResultSet rs;
				      
				      Calendar c1 = Calendar.getInstance();
		                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		                c1.setTime(sdf.parse(text3));
		                c1.add(Calendar.DATE, 21);
		                String date = sdf.format(c1.getTime());
		                
		                String mem = null;
		                String q2 = "SELECT `Membership validity` FROM `customers` WHERE `Customer ID` = " + text5;
		                rs = st.executeQuery(q2);
		                
		                
		                if(rs.next())
		                	mem = rs.getString("Membership validity");
		                
		                String q1;
		                while(true){
		                if(mem.equalsIgnoreCase("Platinum")){
		                	q1 ="SELECT * FROM `books` WHERE `Book Serial no.` = " + text4;
		                	break;
		                }
		                else if(mem.equalsIgnoreCase("Gold")){
		                	q1 = "SELECT * FROM `books` WHERE `Book Serial no.` = " + text4 + " && `MRP` <= 500";
		                	break;
		                }
		                else{
		                	q1 = "SELECT * FROM `books` WHERE `Book Serial no.` = " + text4 + "&& `MRP` <=300";
		                	break;
		                }
		                }
		                String status = null;
		                
		                rs = st.executeQuery(q1);
		                while(rs.next())
		                	status = rs.getString("Book Status");
		                
		                if(status.equalsIgnoreCase("Issued")){
		                	JOptionPane.showMessageDialog(null, "Sorry! This book has already been issued", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		                else
		                	issue_query = "UPDATE `books` SET `Check out date`= '" + text3 + "',`Check in date` = '-',`Due Date` = '" + date + "', `Check out condition`='"+ text1 +"',`Name of Issuee`= '"+ text2 +"',`Book Status`= 'Issued' WHERE `Book Serial no.` = "+ text4+ ";";
		                    JOptionPane.showMessageDialog(null, "Your request is being processed", "Processing....", JOptionPane.PLAIN_MESSAGE);
		               				      
		                    st.executeUpdate(issue_query);    //execute update query
		              
		                    issue_query = "UPDATE `customers` SET `Book Serial no` = '" + text4 + "' WHERE `Customer ID` = '" + text5 + "'";
		                    st.executeUpdate(issue_query);    //execute update query
		                    JOptionPane.showMessageDialog(null, "Request completed succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);       		                
				            System.exit(0);
		                
				}catch(Exception e1){System.out.println(e1);}
			}
			});
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
       IssueBook ib = new IssueBook();
         ib.issueForm();
      }

}
