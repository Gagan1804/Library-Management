
package library_management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AddBook implements ActionListener
{
    JFrame f;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JButton b1,b2;
    JTextField t1,t2,t3,t4,t5,t6;

    AddBook() 
    {
        f= new JFrame("Add/ View/ Delete Books");
        l1= new JLabel("Book Name");
        l2= new JLabel("Author");
        l3= new JLabel("Publisher");
        l4= new JLabel("Genre");
        l5= new JLabel("Book Position:-");
        l6= new JLabel("Shelf");
        l7= new JLabel("Row");
        
        b1= new JButton("Add Book");
        b2= new JButton("Back");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        
        Font font = new Font("Verdana", Font.PLAIN, 16);
        Font grt = new Font("Verdana", Font.BOLD, 16);
        
        t1.setFont(font);  t1.setForeground(Color.BLACK);  t1.setEditable(false);
        t2.setFont(font);  t2.setForeground(Color.BLACK);  t2.setEditable(false);
        t3.setFont(font);  t3.setForeground(Color.BLACK);  t3.setEditable(false);
        t4.setFont(font);  t4.setForeground(Color.BLACK);  t4.setEditable(false);
        t5.setFont(font);  t5.setForeground(Color.BLACK);  t5.setEditable(false);
        t6.setFont(font);  t6.setForeground(Color.BLACK);  t6.setEditable(false);
        
        l1.setFont(font);  l1.setForeground(Color.BLUE);  
        l2.setFont(font);  l2.setForeground(Color.BLUE);  
        l3.setFont(font);  l3.setForeground(Color.BLUE);  
        l4.setFont(font);  l4.setForeground(Color.BLUE);  
        l5.setFont(grt);   l5.setForeground(Color.BLUE);  
        l6.setFont(font);  l6.setForeground(Color.BLUE);  
        l7.setFont(font);  l7.setForeground(Color.BLUE);  
        
        b1.setFont(font);  b1.setForeground(Color.BLACK); 
        
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,100,100,30);
        l3.setBounds(50,150,100,30);
        l4.setBounds(50,200,100,30);
        l5.setBounds(50,380,200,30);
        l6.setBounds(100,440,100,30);
        l7.setBounds(100,500,100,30);
        
        t1.setBounds(160,50,400,30);
        t2.setBounds(160,100,400,30);
        t3.setBounds(160,150,400,30);
        t4.setBounds(160,200,400,30);
        t5.setBounds(210,440,70,30);
        t6.setBounds(210,500,70,30);
        
        b1.setBounds(400,440,150,50);
        b2.setBounds(70,580,100,30);
        
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
        f.add(l1); f.add(l2); f.add(l3); f.add(l4); f.add(l5); f.add(l6); f.add(l7);
        f.add(t1); f.add(t2); f.add(t3); f.add(t4); f.add(t5); f.add(t6);
        f.add(b1); f.add(b2);
        f.setLocation(500,150);
        f.setSize(800,700);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        
    }     

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
            if(ae.getSource()==b1)
            {
                String bn = t1.getText();
                String a = t2.getText();
                String p = t3.getText();
                String g = t4.getText();
                String s = t5.getText();
                String r = t6.getText();

                try {
                    // load driver
                    Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
                    // getConnection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are 
                    // create statement
                    PreparedStatement ps = con.prepareStatement("insert into books(BookName, Author, Publisher, Genre, Shelf, Row) values(?,?,?,?,?,?) ");
                    // execute statement
                    ps.setString(1, bn);
                    ps.setString(2, a);
                    ps.setString(3, p);
                    ps.setString(4, g);
                    ps.setString(5, s);
                    ps.setString(6, r);

                    int i = ps.executeUpdate();
                } 
                catch (Exception e) //try catch use nahin karenge to exception aa jayegi 
                {
                    System.out.println(e);
                }
                JOptionPane.showMessageDialog(b1,"Book is added");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                
            }
            else if(ae.getSource()==b2)
            {
                new LibrarianSuccess();
                f.setVisible(false);
            }
        }
    public static void main(String[] args) 
    {
        new AddBook();
    }
}

