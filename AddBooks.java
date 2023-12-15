package src.Library_package;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class AddBooks {

	JPanel p;
    JFrame f = new JFrame();
    private JTextField bname;
    private JTextField aname;
    private JTextField isbn;
    private JTextField mrp;
    private JTextField loc;
	String query, MRP; int ret;
	
	
	public void returnBook() {
		
		//GUI and its contents
		f.setTitle("Add a Book");
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 275);
		p = new JPanel();
			
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel title = new JLabel("Add a Book to our Library");
		title.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 414, 41);
		p.add(title);
		
		JLabel BookName = new JLabel("Book Name");
		BookName.setBounds(10, 63, 114, 14);
		p.add(BookName);
		
		JLabel AuthorName = new JLabel("Author Name");
		AuthorName.setBounds(10, 88, 114, 14);
		p.add(AuthorName);
		
		JLabel IsbnCode = new JLabel("ISBN code");
		IsbnCode.setBounds(10, 113, 114, 14);
		p.add(IsbnCode);
		
		JLabel lblMrp = new JLabel("M.R.P. ");
		lblMrp.setBounds(10, 138, 46, 14);
		p.add(lblMrp);
		
		bname = new JTextField();
		bname.setBounds(134, 60, 250, 20);
		p.add(bname);
		bname.setColumns(10);
		
		aname = new JTextField();
		aname.setColumns(10);
		aname.setBounds(134, 85, 250, 20);
		p.add(aname);
		
		isbn = new JTextField();
		isbn.setColumns(10);
		isbn.setBounds(134, 110, 250, 20);
		p.add(isbn);
		
		mrp = new JTextField();
		mrp.setColumns(10);
		mrp.setBounds(134, 135, 124, 20);
		p.add(mrp);
		
		JButton submit = new JButton("submit");
		submit.setBounds(335, 202, 89, 23);
		p.add(submit);
		
		JLabel lblBookLocation = new JLabel("Book Location");
		lblBookLocation.setBounds(10, 163, 114, 14);
		p.add(lblBookLocation);
		
		loc = new JTextField();
		loc.setColumns(10);
		loc.setBounds(134, 160, 76, 20);
		p.add(loc);
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//extracting text from text fields
				String Bname = bname.getText();
				String Aname = aname.getText();
				String code = isbn.getText();
				MRP = mrp.getText();
				String location = loc.getText();
				try{
				Class.forName("com.mysql.cj.jdbc.Driver");       //establish connection to SQL database with JDBC driver
				Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
			      Statement st = connect.createStatement();
			      
			      query = "INSERT INTO `books`(`Book Name`, `Author name`, `Check out date`, `Due date` ,`Check in date`, `Check out condition`, `Check in condition`, `Name of Issuee`, `ISBN code`, `Book Location`, `Book Status`, `MRP`) VALUES ('" + Bname + "','" + Aname + "','-','-','-','-','Good','-','" + code + "','"+location+"','Available','"+ MRP +"')";
			      
			      st.executeUpdate(query);       //adding books to the database
			      JOptionPane.showMessageDialog(null, "Thank you for donating a book to our library to help make a better, brighter future");
			      
			      System.exit(0);
			      
			}catch(Exception ex){System.out.println(ex);}
				}
		});
		f.setVisible(true);
	
		
		}
	public static void main(String[] args) {
		AddBooks obj = new AddBooks();
		obj.returnBook();
		}

}
