
package library_management;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class return_book implements ActionListener
{
    JFrame f;
    JLabel l1,l2,l3;
    JButton b1,b2;
    JTextField t1,t2,t3,t4,t5;

    return_book() 
    {
        f= new JFrame("Return");
        l1= new JLabel("Book Name");
        l2= new JLabel("Username");
        l3= new JLabel("Return Date"); 

        
        Date currentDate = Calendar.getInstance().getTime();
        int day = currentDate.getDate();
        int month = currentDate.getMonth();
        int year = currentDate.getYear();
        
        b1= new JButton("Return");
        b2= new JButton("Back");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();

        
        Font font = new Font("Calibri", Font.PLAIN, 20);
        Font grt = new Font("Calibri", Font.ROMAN_BASELINE, 30);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(false);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(false);
        t3.setFont(font);  t3.setForeground(Color.BLACK);  t3.setEditable(false);
        t4.setFont(font);  t4.setForeground(Color.BLACK);  t4.setEditable(false);
        t5.setFont(font);  t5.setForeground(Color.BLACK);  t5.setEditable(false);

        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);  

        
        b1.setFont(grt);  b1.setForeground(Color.BLACK);
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,120,100,30);
        l3.setBounds(50,190,150,30);

        
        t1.setBounds(170,50,400,30);
        t2.setBounds(170,120,400,30);
        t3.setBounds(170,190,70,30);
        t4.setBounds(250,190,70,30);
        t5.setBounds(330,190,70,30);

        
        b1.setBounds(200,300,150,50);
        b2.setBounds(70,480,70,30);
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        // Importing and Setting current date month and year      ** Very important....remember this
        Calendar cal = Calendar.getInstance();
        t3.setText(String.valueOf(cal.get(Calendar.DATE)));
        t4.setText(String.valueOf(cal.get(Calendar.MONTH)+1));
        t5.setText(String.valueOf(cal.get(Calendar.YEAR)));
        
        
        t1.setEditable(true);
        t2.setEditable(true);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);

     
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        f.add(l1); f.add(l2); f.add(l3);
        f.add(t1); f.add(t2); f.add(t3); f.add(t4); f.add(t5);
        f.add(b1); f.add(b2);
        f.setSize(700,600);
        f.setLocation(500,150);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    } 
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {
                String b = t1.getText();
                String u = t2.getText();
            try
            {
            Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
            // getConnection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123");
            PreparedStatement ps = con.prepareStatement("select * from issue_book where Bookname=? && Username=?");
            // execute statement
            
            ps.setString(1, b);
            ps.setString(2, u);
            ResultSet rs = ps.executeQuery(); // executeQuery return type= ResultSet 
       
            if(rs.next())  // matlab agar yeh condition true ho gayi to yaani agar database mein uss bande ke account ka username aur password agar iss window mein entered username aur password se match kar gayi to yeh if wali condition chalegi
            {
                 try 
                {
                    String bn = t1.getText();
                    String un = t2.getText();
                    // load driver
                    Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
                    // getConnection
                    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are username and password for mysql
                    // create statement                                                       -database ka name hai library1                
                    PreparedStatement ps1 = con1.prepareStatement("insert into returned_books(BookName, Username) values (?,?) ");
                    // execute statement
                    ps.setString(1, bn);
                    ps.setString(2, un);

                    int i = ps.executeUpdate();
                } 
                catch (Exception yo) //try catch use nahin karenge to exception aa jayegi 
                {
                    System.out.println(yo);
                }
                f.setVisible(false);
            }
            else 
            {
                JOptionPane.showMessageDialog(b1,"This book or username does not exist ( i.e. The details which you entered)");
                t1.setText("");
                t2.setText("");
            }   
                }
                catch(Exception f)
                {
                    System.out.println(f);
                }
               
            }
            else if(e.getSource()==b2)
            {
                new LibrarianSuccess();
                f.setVisible(false);
            }
            

        }
    
    public static void main(String[] args) 
    {
        new return_book();
    }
    
}



