
package library_management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class DeleteBook implements ActionListener
{
    JFrame f;
    JLabel l1,l2,l3;
    JTextField t1,t2;
    JPasswordField p;
    JButton b1,b2;

    public DeleteBook() 
    {
        f=new JFrame("Delete Book");
        l1=new JLabel("BookName");
        l2=new JLabel("Username");
        l3=new JLabel("Password");
        t1=new JTextField();
        t2=new JTextField();
        p=new JPasswordField();
        b1=new JButton("Delete");
        b2=new JButton("Back");
        
        Font font = new Font("Calibri", Font.PLAIN, 20);
        Font grt = new Font("Calibri", Font.ROMAN_BASELINE, 30);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(true);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(true);
        p.setFont(font);  p.setForeground(Color.BLACK);  p.setEditable(true);

        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);  

        
        b1.setFont(grt);  b1.setForeground(Color.BLACK);
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,120,100,30);
        l3.setBounds(50,190,150,30);

        
        t1.setBounds(170,50,300,30);
        t2.setBounds(170,120,300,30);
        p.setBounds(170,190,300,30);

        
        b1.setBounds(200,300,150,50);
        b2.setBounds(100,450,70,30);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.add(l1); f.add(l2); f.add(l3);
        f.add(t1); f.add(t2); f.add(p);
        f.add(b1); f.add(b2);
        f.setSize(700,600);
        f.setLocation(600,150);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent o) 
    {   
        if(o.getSource()==b1)
        {
            String bn = t1.getText();
            try {
                    // load driver
                    Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
                    // getConnection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are 
                    // create statement
                   PreparedStatement ps=con.prepareStatement("delete from books where BookName=?");
                    // execute statement
                    ps.setString(1, bn);

                    int i = ps.executeUpdate();
                    JOptionPane.showMessageDialog(b1, "Book is Deleted","Deleted!", JOptionPane.ERROR_MESSAGE);
            }
            
            catch(Exception e) 
            {
                System.out.println(e);
            }
        }
            else if(o.getSource()==b2)
            {
                new LibrarianSuccess();
                f.dispose();
            }
    }
   
    
    
    public static void main(String[] args) 
    {
        new DeleteBook();
    }
}
