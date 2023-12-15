package src.Library_package;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class EditBookDetails {

	private JFrame f;
	private JPanel p;
	private JTextField bname;
	private JTextField aname;
	private JTextField isbn;
	private JTextField loc;
	private JTextField mrp;
	String Bname, Aname, MRP, ISBN, Loc;
	ResultSet rs;
	public void edit(){
		
		String BSN = JOptionPane.showInputDialog("Enter Book Serial no. to edit book details : ");    //get Book serial no.
		try{
		     Class.forName("com.mysql.cj.jdbc.Driver"); //Connecting to SQL database through JDBC driver
		     Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		     Statement st = connect.createStatement();	
		     
		     String query = "SELECT * FROM books WHERE `Book Serial no.` =" + BSN;
		     rs = st.executeQuery(query);
		}catch(Exception ex){System.out.println(ex);}
		//GUI and components
		f = new JFrame();
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		f.setVisible(true);		     
		f.setTitle("LibSys");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		p = new JPanel();
		f.setContentPane(p);
		p.setLayout(null);
		
		JLabel Title = new JLabel("Edit Book Details");
		Title.setFont(new Font("Sitka Banner", Font.PLAIN, 24));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(5, 5, 424, 41);
		p.add(Title);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(15, 57, 92, 14);
		p.add(lblBookName);
		
		JLabel lblAuthorName = new JLabel("Author Name");
		lblAuthorName.setBounds(15, 82, 92, 14);
		p.add(lblAuthorName);
		
		JLabel lblIsbnCode = new JLabel("ISBN code");
		lblIsbnCode.setBounds(15, 107, 92, 14);
		p.add(lblIsbnCode);
		
		JLabel lblBookLocation = new JLabel("Book Location");
		lblBookLocation.setBounds(15, 132, 92, 14);
		p.add(lblBookLocation);
		
		JLabel lblMrp = new JLabel("MRP");
		lblMrp.setBounds(15, 157, 92, 14);
		p.add(lblMrp);
		try{
			while(rs.next()){
		bname = new JTextField(rs.getString("Book Name"));
		bname.setBounds(117, 54, 286, 20);
		p.add(bname);
		bname.setColumns(10);
		
		aname = new JTextField(rs.getString("Author Name"));
		aname.setColumns(10);
		aname.setBounds(117, 79, 286, 20);
		p.add(aname);
		
		isbn = new JTextField(rs.getString("ISBN code"));
		isbn.setColumns(10);
		isbn.setBounds(117, 104, 286, 20);
		p.add(isbn);
		
		loc = new JTextField(rs.getString("Book Location"));
		loc.setColumns(10);
		loc.setBounds(117, 129, 286, 20);
		p.add(loc);
		
		mrp = new JTextField(rs.getString("MRP"));
		mrp.setColumns(10);
		mrp.setBounds(117, 154, 286, 20);
		p.add(mrp);
			}
			}catch(Exception ex){System.out.println(ex);}
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(340, 185, 89, 23);
		p.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				     Class.forName("com.mysql.cj.jdbc.Driver"); //Connecting to SQL database through JDBC driver
				     Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
				     Statement st = connect.createStatement();	
				while(true){	 
					
					//extracting text from text fields
					 String Bname = bname.getText();
					 String Aname = aname.getText();
					 String ISBN = isbn.getText();
					 String Loc = loc.getText();
					 String MRP = mrp.getText();
					 if(Bname == null || Aname == null || ISBN == null|| Loc == null || MRP == null)  //checking if all fields are filled
						 JOptionPane.showMessageDialog(null, "Please fill in all details", null, JOptionPane.ERROR_MESSAGE);
					 else
						 break;
				}
					 if(JOptionPane.showConfirmDialog(null, "Do you wish to edit the details of the book?") == JOptionPane.YES_OPTION){
					    String query = "UPDATE `books` SET `Book Name` = '" + Bname + "', `Author name` = '" + Aname + "',`ISBN code` = '" + ISBN + "', `Book Location` = '" + Loc + "', `MRP` = " + MRP + " WHERE `Book Serial no.` = " + BSN;
				        st.executeUpdate(query); //executing the update query
				        System.exit(0);
					 }
				}catch(Exception ex){System.out.println(ex);}
			}
	    });
	}
	public static void main(String[] args) {
		EditBookDetails e1 = new EditBookDetails();
	    e1.edit();
	}
}