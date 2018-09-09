
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


public class AddUser implements ActionListener
{
    JFrame f;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JButton b1,b2;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8;

    public AddUser() 
    {
        f = new JFrame("");
        l1= new JLabel("Username");
        l2= new JLabel("Password");
        l3= new JLabel("Position");
        l4= new JLabel("Email");
        l5= new JLabel("Academic :- ");
        l6= new JLabel("First Name");
        l7= new JLabel("Last Name");
        l8= new JLabel("Program");
        l9= new JLabel("Year");

        
        b1= new JButton("Add User");
        b2= new JButton("Back");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        t7 = new JTextField();
        t8 = new JTextField();
        
        Font font = new Font("Calibri", Font.PLAIN, 20);
        Font grt = new Font("Calibri", Font.ROMAN_BASELINE, 30);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(true);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(true);
        t3.setFont(font);  t3.setForeground(Color.BLACK);  t3.setEditable(true);
        t4.setFont(font);  t4.setForeground(Color.BLACK);  t4.setEditable(true);
        t5.setFont(font);  t5.setForeground(Color.BLACK);  t5.setEditable(true);
        t6.setFont(font);  t6.setForeground(Color.BLACK);  t6.setEditable(true);
        t7.setFont(font);  t7.setForeground(Color.BLACK);  t7.setEditable(true);
        t8.setFont(font);  t8.setForeground(Color.BLACK);  t8.setEditable(true);

        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);
        l4.setFont(font);  l4.setForeground(Color.BLUE);
        l5.setFont(font);  l5.setForeground(Color.BLUE);
        l6.setFont(font);  l6.setForeground(Color.BLUE);
        l7.setFont(font);  l7.setForeground(Color.BLUE);
        l8.setFont(font);  l8.setForeground(Color.BLUE);
        l9.setFont(font);  l9.setForeground(Color.BLUE);
        
        
        b1.setFont(grt);  b1.setForeground(Color.BLACK);
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,100,100,30);
        l3.setBounds(50,150,150,30);
        l4.setBounds(50,200,150,30);
        l6.setBounds(50,250,150,30);
        l7.setBounds(50,300,150,30);
        l5.setBounds(50,370,150,30);
        l8.setBounds(70,405,150,30);
        l9.setBounds(70,445,150,30);

        
        t1.setBounds(170,50,400,30);
        t2.setBounds(170,100,400,30);
        t3.setBounds(170,150,400,30);
        t4.setBounds(170,200,400,30);
        t5.setBounds(170,250,400,30);
        t6.setBounds(170,300,400,30);
        t7.setBounds(170,405,150,30);
        t8.setBounds(170,445,100,30);

        
        b1.setBounds(300,520,150,50);
        b2.setBounds(70,600,70,30);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        t1.setEditable(true);
        t2.setEditable(true);
        t3.setEditable(true);
        t4.setEditable(true);
        t5.setEditable(true);
        t6.setEditable(true);
        

        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("src/library_management/5.jpg")));
        f.setLayout(new FlowLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("src/library_management/0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.add(l1); f.add(l2); f.add(l3);f.add(l4); f.add(l5); f.add(l6); f.add(l7);f.add(l8); f.add(l9);
        f.add(t1); f.add(t2); f.add(t3); f.add(t4); f.add(t5); f.add(t6); f.add(t7); f.add(t8);
        f.add(b1); f.add(b2);
        f.setSize(800,730);
        f.setLocation(400,150);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    } 
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==b1)
            {
                String usr = t1.getText();
                String pass = t2.getText();
                String pos = t3.getText();
                String eml = t4.getText();
                String fn = t5.getText();
                String ln = t6.getText();
                String pg = t7.getText();
                String yr = t8.getText();
                
                try 
                {
                    // load driver
                    Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
                    // getConnection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are 
                    // create statement
                    PreparedStatement ps = con.prepareStatement("insert into account_details(Username, Password, Position, Email ,Firstname, Lastname, Program, Year) values(?,?,?,?,?,?,?,?) ");
                    // execute statement
                    ps.setString(1, usr);
                    ps.setString(2, pass);
                    ps.setString(3, pos);
                    ps.setString(4, eml);
                    ps.setString(5, fn);
                    ps.setString(6, ln);
                    ps.setString(7, pg);
                    ps.setString(8, yr);
                    int i = ps.executeUpdate();
                    JOptionPane.showMessageDialog(b1,"User has been successfully added");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");    
                } 
                catch (Exception yo) //try catch use nahin karenge to exception aa jayegi 
                {
                    System.out.println(yo);
                }
                
            }
            else if(ae.getSource()==b2)
            {
                new LibrarianSuccess();
                f.setVisible(false);
            }

        }
    public static void main(String[] args) 
    {
        new AddUser();
    }
}
    
    
   
        
    
    

