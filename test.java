package src.Library_package;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;


public class test {

	private JPanel p;
    private JFrame f;
    Process pr;
	public static void main(String[] args) {
		new test();
	}

	public test() {
		f = new JFrame("Starting....");
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 446, 188);
		p = new JPanel();
		
		f.setContentPane(p);
		p.setLayout(null);
		
		JButton b = new JButton();
		f.setResizable(false);
		b.setBounds(10, 10, 170, 138);
		 try {
			    Image img = ImageIO.read(getClass().getResource("Logo.png"));
			    b.setIcon(new ImageIcon(img));
			  } catch (Exception ex) {
			    System.out.println(ex);
			  }
		p.add(b);
		
		JTextArea l1 = new JTextArea("LibSys Library Management Software");
		l1.setFont(new Font("Sitka Banner", Font.PLAIN, 23));
		l1.setBounds(212, 21, 194, 86);
		p.add(l1);
		l1.setWrapStyleWord(true);
		l1.setLineWrap(true);
		
		JProgressBar pb = new JProgressBar(0,2000);
		pb.setBounds(212, 118, 194, 14);
		p.add(pb);
		try{
    		Runtime rt = Runtime.getRuntime();
        	pr = rt.exec("C:\\MAMP\\MAMP.exe");
        	pr.waitFor();
        	for(int i = 0; i<=2000; i+=25){
    			pb.setValue(i);
    			try{
    				Thread.sleep(1000);
    			}catch(Exception e){System.out.println(e);}
    			
		}
		
		}catch(Exception e){System.out.println(e);}
		f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));		
	    StartPage.main(null);
}
}
