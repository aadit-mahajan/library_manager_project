package src.Library_package;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
public class ViewBookDetails{
	
	JFrame f = new JFrame("View Book Details");
	
    JPanel p = new JPanel();
	String sno, Bname, Aname, cin, cout, cond1, cond2, cname, isbn, loc, stat;
	
	public void getConnection(){
		try {
			String input = JOptionPane.showInputDialog(null, "Enter Book Serial no. :");	
			String query = "SELECT * FROM books WHERE `Book Serial no.` =" + input ;
			Thread.sleep(1000);
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection connect = DriverManager.getConnection("jdbc:mysql://::1:3306/library","root","root");
		      Statement st = connect.createStatement();
		      ResultSet rs;
		      
		     rs = st.executeQuery(query);
		     while(rs.next()){
		    	 //extracting text from resultset rs
		    	 sno = rs.getString("Book Serial no.");
		    	 Bname = rs.getString("Book Name");
		    	 Aname = rs.getString("Author Name");
		    	 cin = rs.getString("Check in date");
		    	 cout = rs.getString("Check out date");
		    	 cond1 = rs.getString("Check in condition");
		    	 cond2 = rs.getString("Check out condition");
		    	 cname = rs.getString("Name of Issuee");
		    	 isbn = rs.getString("ISBN code");
		    	 loc = rs.getString("Book Location");
		    	 stat = rs.getString("Book Status");
		    	 System.out.println(sno + " " + Bname + " " + Aname + " " + cin +  " " +cout +  " " +cond1 +  " " +cond2 +  " " +cname +  " " +isbn +  " " +loc +  " " +stat);
		    	 
		     }
		    	 
				} catch (Exception e) {
					System.out.println(e);
				}
		//GUI and its components
		f.getContentPane().setLayout(null);
		f.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
		JLabel title = new JLabel("Book Details - " + Bname);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		title.setBounds(10, 11, 414, 31);
		f.getContentPane().add(title);
		
		JLabel lblBookSerialNo = new JLabel("Book Serial no.");
		lblBookSerialNo.setBounds(20, 53, 106, 14);
		f.getContentPane().add(lblBookSerialNo);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(20, 78, 106, 14);
		f.getContentPane().add(lblBookName);
		
		JLabel lblAuthorName = new JLabel("Author Name");
		lblAuthorName.setBounds(20, 103, 106, 14);
		f.getContentPane().add(lblAuthorName);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(20, 128, 46, 14);
		f.getContentPane().add(lblStatus);
		
		JLabel lblIsbnCode = new JLabel("ISBN code");
		lblIsbnCode.setBounds(20, 153, 106, 14);
		f.getContentPane().add(lblIsbnCode);
		
		JLabel BSN = new JLabel(sno);
		BSN.setBounds(136, 53, 288, 14);
		f.getContentPane().add(BSN);
		
		JLabel bname = new JLabel(Bname);
		bname.setBounds(136, 78, 288, 14);
		f.getContentPane().add(bname);
		
		JLabel aname = new JLabel(Aname);
		aname.setBounds(136, 103, 288, 14);
		f.getContentPane().add(aname);
		
		JLabel status = new JLabel(stat);
		status.setBounds(136, 128, 288, 14);
		f.getContentPane().add(status);
		
		JLabel ISBN = new JLabel(isbn);
		ISBN.setBounds(136, 153, 288, 14);
		f.getContentPane().add(ISBN);
		f.setForeground(Color.LIGHT_GRAY);
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(500, 300);
	}
	
   
	
	public static void main(String[] args) {
		ViewBookDetails obj =  new ViewBookDetails();
		obj.getConnection();
		
		
			}
}