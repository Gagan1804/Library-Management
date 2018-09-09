
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

public class issue_book implements ActionListener
{
    JFrame f;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JButton b1,b2;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8;

    issue_book() 
    {
        f= new JFrame("Issue");
        l1= new JLabel("Book name");
        l2= new JLabel("Username");
        l3= new JLabel("Issue Date"); 
        l4= new JLabel("Return Date");
        l5= new JLabel("Enter   (date)");
        l6= new JLabel("(month)"); 
        l7= new JLabel(" (year)");
        
        
        Date currentDate = Calendar.getInstance().getTime();
        int day = currentDate.getDate();
        int month = currentDate.getMonth();
        int year = currentDate.getYear();
        
        b1= new JButton("Issue");
        b2= new JButton("Back");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t2.setToolTipText("Enter Username of user whom you are issuing this book");
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        t7 = new JTextField();
        t8 = new JTextField();
        
        Font font = new Font("Calibri", Font.PLAIN, 20);
        Font fr = new Font("Calibri", Font.PLAIN, 16);
        Font grt = new Font("Calibri", Font.ROMAN_BASELINE, 30);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(false);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(false);
        t3.setFont(font);  t3.setForeground(Color.BLACK);  t3.setEditable(false);
        t4.setFont(font);  t4.setForeground(Color.BLACK);  t4.setEditable(false);
        t5.setFont(font);  t5.setForeground(Color.BLACK);  t5.setEditable(false);
        t6.setFont(font);  t6.setForeground(Color.BLACK);  t6.setEditable(false);
        t7.setFont(font);  t7.setForeground(Color.BLACK);  t7.setEditable(false);
        t8.setFont(font);  t8.setForeground(Color.BLACK);  t8.setEditable(false);
        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);  
        l4.setFont(font);  l4.setForeground(Color.BLUE);
        l5.setFont(fr);  l5.setForeground(Color.RED);  
        l6.setFont(fr);  l6.setForeground(Color.RED);  
        l7.setFont(fr);  l7.setForeground(Color.RED);
        
        b1.setFont(grt);  b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,100,140,30);
        l3.setBounds(50,150,150,30);
        l4.setBounds(50,200,150,30);
        l5.setBounds(130,230,140,30);
        l6.setBounds(250,230,70,30);
        l7.setBounds(330,230,70,30);
        //l8.setBounds(330,230,70,30);
        
        t1.setBounds(160,50,400,30);
        t2.setBounds(160,100,400,30);
        t3.setBounds(160,150,70,30);
        t4.setBounds(240,150,70,30);
        t5.setBounds(320,150,70,30);
        t6.setBounds(160,200,70,30);
        t7.setBounds(240,200,70,30);
        t8.setBounds(320,200,70,30);
        
        b1.setBounds(200,300,150,50);
        b2.setBounds(70,480,70,30);
        
        
        // Importing and Setting current date month and year      ** Very important....remember this
        Calendar cal = Calendar.getInstance();
        t3.setText(String.valueOf(cal.get(Calendar.DATE)));
        t4.setText(String.valueOf(cal.get(Calendar.MONTH)+1));
        t5.setText(String.valueOf(cal.get(Calendar.YEAR)));
        // *************************************************************************************** //
        
        t1.setEditable(true);
        t2.setEditable(true);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(true);
        t7.setEditable(true);
        t8.setEditable(true);
     
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.add(l1); f.add(l2); f.add(l3); f.add(l4); f.add(l5); f.add(l6); f.add(l7);
        f.add(t1); f.add(t2); f.add(t3); f.add(t4); f.add(t5); f.add(t6); f.add(t7); f.add(t8);
        f.add(b1); f.add(b2);
        f.setSize(750,600);
        f.setLocation(500,150);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        

        
    } 
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {
                String bid = t1.getText();
                String usrid = t2.getText();
                String isd = t3.getText();
                String ism = t4.getText();
                String isy = t5.getText();
                String rd = t6.getText();
                String rm = t7.getText();
                String ry = t8.getText();

                try {
                    // load driver
                    Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
                    // getConnection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are username and password for mysql
                    // create statement                                                       -database ka name hai library1                
                    PreparedStatement ps = con.prepareStatement("insert into issue_book(BookName, Username, IssueDate, IssueMonth, IssueYear, ReturnDate, ReturnMonth, ReturnYear) values(?,?,?,?,?,?,?,?) ");
                    // execute statement
                    ps.setString(1, bid);
                    ps.setString(2, usrid);
                    ps.setString(3, isd);
                    ps.setString(4, ism);
                    ps.setString(5, isy);
                    ps.setString(6, rd);
                    ps.setString(7, rm);
                    ps.setString(8, ry);

                    int i = ps.executeUpdate();
                } 
                catch (Exception yo) //try catch use nahin karenge to exception aa jayegi 
                {
                    System.out.println(yo);
                }
                f.setVisible(false);
            }
            else if(e.getSource()==b2)
            {
                new LibrarianSuccess(); 
                f.setVisible(false);
            }

        }
    
    public static void main(String[] args) 
    {
        new issue_book();
    }
    
}


