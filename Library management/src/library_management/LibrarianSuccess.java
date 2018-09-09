
package library_management;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LibrarianSuccess implements ActionListener
{
    static JLabel l1,l2,l3,l4;
    static JFrame f;
    JTextField t1,t2,t3;
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    
    LibrarianSuccess() 
    {
        f= new JFrame("Admin Controls");
        l1= new JLabel("Name");
        l2= new JLabel("Library ID");
        l3= new JLabel("Email");
        l4 = new JLabel();

        l4.setIcon(new ImageIcon(new ImageIcon("src/library_management/librarian.gif").getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT)));

        b1= new JButton("Log Out");
        b2= new JButton("Issue book");
        b3= new JButton("Return book");
        b4= new JButton("Delete book");
        b5= new JButton("Add Students");
        b6= new JButton("View Student");
        b7= new JButton("Add book");
        b8= new JButton("View book");
       
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        
        Font font = new Font("Verdana", Font.PLAIN, 16);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(false);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(false);
        t3.setFont(font);  t3.setForeground(Color.BLACK);  t3.setEditable(false);
        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);
        
        b1.setFont(font);  b1.setForeground(Color.RED);
        b2.setFont(font);  b2.setForeground(Color.BLACK);
        b3.setFont(font);  b3.setForeground(Color.BLACK);
        b4.setFont(font);  b4.setForeground(Color.BLACK);
        b5.setFont(font);  b5.setForeground(Color.BLACK);
        b6.setFont(font);  b6.setForeground(Color.BLACK);
        b7.setFont(font);  b7.setForeground(Color.BLACK);
        b8.setFont(font);  b8.setForeground(Color.BLACK);
       
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,100,100,30);
        l3.setBounds(50,150,100,30);
        l4.setBounds(30,200,400,400);
        
        t1.setBounds(150,50,300,30);
        t2.setBounds(150,100,300,30);
        t3.setBounds(150,150,300,30);
        
        b1.setBounds(500,560,200,50);
        b2.setBounds(500,100,150,50);
        b3.setBounds(500,160,150,50);
        b4.setBounds(500,220,150,50);
        b5.setBounds(500,280,150,50);
        b6.setBounds(500,340,150,50);
        b7.setBounds(500,400,150,50);
        b8.setBounds(500,460,150,50);
       

        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        
        f.add(l1); f.add(l2); f.add(l3);f.add(l4);
        f.add(t1); f.add(t2); f.add(t3);

        f.add(b1); f.add(b2); f.add(b3); f.add(b4); f.add(b5); f.add(b6); f.add(b7); f.add(b8);
        f.setSize(800,700);
        f.setLocation(500,150);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
       
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == b1)
        {
            new Main_page();
            f.dispose();
        }
        else if(ae.getSource() == b2)
        {
            new issue_book();
            f.dispose();
        }
        else if(ae.getSource() == b3)
        {
            new return_book();
            f.dispose();
        }
        else if(ae.getSource() == b4)
        {
            new DeleteBook();
            f.dispose();
        }
        else if(ae.getSource() == b5)
        {
            new AddUser();
            f.dispose();
        }
        else if(ae.getSource() == b6)
        {
            new ViewStudents();
            f.dispose();
        }
        else if(ae.getSource() == b7)
        {
            new AddBook();
            f.dispose();
        }

        else if(ae.getSource() == b8)
        {
            System.out.println(b8.getText());
            System.out.println("ba");
     
            new Viewbooks();
            f.dispose();
        }
    }
    
    public static void main(String[] args) 
    {
        //new LibrarianSuccess();//  yeh bhi lagayenge to 2 windows khul jayengi same yehi wali... kyunki jo neeche 2 lines likhi hain usse bhi kaam ban jayega
        LibrarianSuccess at = new LibrarianSuccess();
        at.admin_details();
    }

    public void admin_details() 
    {
        String n ="";
        String li ="";
        String em ="";
       try(Connection Con = Db.getConnection()) 
        {
            PreparedStatement ps=Con.prepareStatement("select * from admin_details where LibraryID= ?");
            ps.setInt(1, 1);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                n = rs.getString("Name");
                li = rs.getString("LibraryID");
                em = rs.getString("Email");
            }
            t1.setText(n);
            t2.setText(li);
            t3.setText(em);
        }
       catch(Exception t)
       {
           System.out.println(t);
       }
    }
}


