
package library_management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;


public class user_login_accounts_creation implements ActionListener
{
    JFrame f;
    JLabel l1,l2,l3,l4;
    JButton b1,b2;
    JTextField t1,t2,t3,t4;

    public user_login_accounts_creation() 
    {
        f = new JFrame("");
        l1= new JLabel("First Name");
        l2= new JLabel("Last Name");
        l3= new JLabel("Username");
        l4= new JLabel("Password");


        
        b1= new JButton("Create Account");
        b2= new JButton("Back");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        
        Font font = new Font("Calibri", Font.PLAIN, 20);
        Font grt = new Font("Calibri", Font.ROMAN_BASELINE, 30);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(true);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(true);
        t3.setFont(font);  t3.setForeground(Color.BLACK);  t3.setEditable(true);
        t4.setFont(font);  t4.setForeground(Color.BLACK);  t4.setEditable(true);


        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);
        l4.setFont(font);  l4.setForeground(Color.BLUE);

        
        b1.setFont(grt);  b1.setForeground(Color.BLACK);
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,100,100,30);
        l3.setBounds(50,150,150,30);
        l4.setBounds(50,200,150,30);


        
        t1.setBounds(170,50,400,30);
        t2.setBounds(170,100,400,30);
        t3.setBounds(170,150,400,30);
        t4.setBounds(170,200,400,30);


        
        b1.setBounds(240,500,240,50);
        b2.setBounds(70,600,70,30);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        t1.setEditable(true);
        t2.setEditable(true);
        t3.setEditable(true);
        t4.setEditable(true);

        

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        
        f.add(l1); f.add(l2); f.add(l3);f.add(l4);
        f.add(t1); f.add(t2); f.add(t3); f.add(t4);
        f.add(b1); f.add(b2);
        f.setLocation(500,100);
        f.setSize(800,730);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    } 
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==b1)
            {
                String fn = t1.getText();
                String ln = t2.getText();
                String usr = t3.getText();
                String pass = t4.getText();
                
                try 
                {
                    // load driver
                    Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
                    // getConnection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are 
                    // create statement
                    PreparedStatement ps = con.prepareStatement("insert into user_login_accounts(Firstname, Lastname, Username, Password) values(?,?,?,?)");
                    // execute statement
                    ps.setString(1, fn);
                    ps.setString(2, ln);
                    ps.setString(3, usr);
                    ps.setString(4, pass);

                    int i = ps.executeUpdate();
                    JOptionPane.showMessageDialog(b1,"Your account has been created successfully....you can now login");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
  
                } 
                catch (Exception yo) //try catch use nahin karenge to exception aa jayegi 
                {
                    System.out.println(yo);
                }
                
            }
            else if(ae.getSource()==b2)
            {
                new user_login();
                f.setVisible(false);
            }

        }
    public static void main(String[] args) 
    {
        new user_login_accounts_creation();
    }
}
    
    
   
        
    
    


